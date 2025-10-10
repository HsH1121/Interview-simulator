package com.example.Interview.simulator.service;

import com.example.Interview.simulator.dto.LoginRequest;
import com.example.Interview.simulator.dto.MemberResponse;
import com.example.Interview.simulator.dto.SignUpRequest;
import com.example.Interview.simulator.entity.Member;
import com.example.Interview.simulator.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public MemberResponse signUp(SignUpRequest req) {
        if (memberRepository.existsByEmail(req.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.");
        }
        Member member = Member.builder()
                .email(req.getEmail())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .nickname(req.getNickname())
                .build();
        Member saved = memberRepository.save(member);
        return MemberResponse.from(saved);
    }

    // 로그인
    @Transactional(readOnly = true)
    public MemberResponse login(LoginRequest req) {
        Member member = memberRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "가입되지 않은 이메일입니다."));
        if (!passwordEncoder.matches(req.getPassword(), member.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return MemberResponse.from(member);
    }

    // 이메일로 조회
    @Transactional(readOnly = true)
    public MemberResponse findByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다."));
        return MemberResponse.from(member);
    }
}
