package com.example.Interview.simulator.service;

import com.example.Interview.simulator.dto.LoginRequest;
import com.example.Interview.simulator.dto.MemberResponse;
import com.example.Interview.simulator.dto.SignupRequest;
import com.example.Interview.simulator.entity.Member;
import com.example.Interview.simulator.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MemberResponse signup(SignupRequest req) {
        if (!req.isTerms()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이용약관에 동의해야 합니다.");
        }
        if (req.getPassword() == null || !req.getPassword().equals(req.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        String email = req.getEmail() == null ? null : req.getEmail().toLowerCase();

        if (memberRepository.existsByEmailIgnoreCase(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.");
        }
        if (memberRepository.existsByNickname(req.getNickname())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다.");
        }

        String hash = passwordEncoder.encode(req.getPassword());
        Member member = req.toEntity(hash);
        member.setEmail(email);

        Member saved = memberRepository.save(member);
        return MemberResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public MemberResponse login(LoginRequest req) {
        String email = req.getEmail() == null ? null : req.getEmail().toLowerCase();

        Member member = memberRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(req.getPassword(), member.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public MemberResponse findByEmail(String emailRaw) {
        String email = emailRaw == null ? null : emailRaw.toLowerCase();

        Member member = memberRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다."));
        return MemberResponse.from(member);
    }
}
