package com.example.Interview.simulator.controller;

import com.example.Interview.simulator.dto.LoginRequest;
import com.example.Interview.simulator.dto.SignUpRequest;
import com.example.Interview.simulator.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/auth")
public class AuthPageController {

    private final AuthService authService;

    public AuthPageController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signup")
    public String signupPage(HttpServletRequest req, Model model) {
        model.addAttribute("_csrf", req.getAttribute("_csrf"));
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute SignUpRequest req, RedirectAttributes ra) {
        try {
            authService.signUp(req);
            ra.addFlashAttribute("ok", "회원가입이 완료되었습니다.");
            return "redirect:/auth/login";
        } catch (ResponseStatusException e) {
            ra.addFlashAttribute("err", e.getReason());
            return "redirect:/auth/signup";
        }
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest req, Model model) {
        model.addAttribute("_csrf", req.getAttribute("_csrf"));
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginRequest req, RedirectAttributes ra) {
        try {
            var member = authService.login(req);
            ra.addFlashAttribute("ok", "로그인 성공: " + member.getNickname());
            return "redirect:/";
        } catch (ResponseStatusException e) {
            ra.addFlashAttribute("err", e.getReason());
            return "redirect:/auth/login";
        }
    }
}
