package com.example.Interview.simulator.api;

import com.example.Interview.simulator.dto.LoginRequest;
import com.example.Interview.simulator.dto.MemberResponse;
import com.example.Interview.simulator.dto.SignUpRequest;
import com.example.Interview.simulator.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final AuthService authService;

    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signUp(@RequestBody SignUpRequest req) {
        return ResponseEntity.ok(authService.signUp(req));
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }
}
