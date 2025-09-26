package com.example.Interview.simulator.controller;

import com.example.Interview.simulator.dto.LoginRequest;
import com.example.Interview.simulator.dto.MemberResponse;
import com.example.Interview.simulator.dto.SignupRequest;
import com.example.Interview.simulator.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// 컨트롤러는 DTO만 받고 서비스만 호출
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public MemberResponse signup(@Valid @RequestBody SignupRequest req) {
        return memberService.signup(req);
    }

    // 로그인
    @PostMapping("/login")
    public MemberResponse login(@Valid @RequestBody LoginRequest req, HttpServletRequest httpRequest) {
        // 서비스에서 인증
        MemberResponse res = memberService.login(req);
        // SecurityContext 설정 및 세션 저장
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                req.getEmail(), null, SecurityContextHolder.getContext().getAuthentication() == null
                ? null : SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        return res;
    }

    // 내 정보
    @GetMapping("/me")
    public MemberResponse me(Authentication authentication) {
        // 인증된 사용자의 이메일로 조회
        String email = authentication.getName();
        return memberService.me(email);
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
    }
}
