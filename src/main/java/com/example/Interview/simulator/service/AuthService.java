package com.example.Interview.simulator.service;

import com.example.interview.simulator.dto.LoginRequest;
import com.example.interview.simulator.dto.MemberResponse;
import com.example.interview.simulator.dto.SignupRequest;
import com.example.interview.simulator.entity.Member;
import com.example.interview.simulator.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    // 레포지토리
    private final MemberRepository memberRepository;
    // 패스워드 인코더
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public MemberResponse signup(SignupRequest req) {
        // 이메일 중복 확인
        if (memberRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        // 해시 생성
        String hash = passwordEncoder.encode(req.getPassword());
        // 엔티티 생성
        Member member = Member.builder()
                .email(req.getEmail())
                .passwordHash(hash)
                .nickname(req.getNickname())
                .build();
        // 저장
        Member saved = memberRepository.save(member);
        // 응답
        return new MemberResponse(saved.getId(), saved.getEmail(), saved.getNickname(), saved.getCreatedAt());
    }

    // 로그인
    @Transactional(readOnly = true)
    public MemberResponse login(LoginRequest req, HttpServletRequest httpRequest) {
        // 사용자 조회
        Member m = memberRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        // 비밀번호 검증
        if (!passwordEncoder.matches(req.getPassword(), m.getPasswordHash())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        // 권한 구성
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        // 인증 토큰 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(m.getEmail(), null, authorities);
        // 컨텍스트 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 세션에 컨텍스트 저장
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        // 응답
        return new MemberResponse(m.getId(), m.getEmail(), m.getNickname(), m.getCreatedAt());
    }

    // 현재 사용자 조회
    @Transactional(readOnly = true)
    public MemberResponse me(String email) {
        Member m = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        return new MemberResponse(m.getId(), m.getEmail(), m.getNickname(), m.getCreatedAt());
    }

    // 로그아웃
    @Transactional(readOnly = true)
    public void logout(HttpServletRequest request) {
        // 세션 무효화 및 컨텍스트 초기화
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        SecurityContextHolder.clearContext();
    }
}
