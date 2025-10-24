package com.example.Interview.simulator.controller;

import com.example.Interview.simulator.service.InterviewSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller @RequiredArgsConstructor
public class PageController {
    private final InterviewSessionService sessionService;

    @GetMapping("/onboarding")      public String onboarding() { return "view/onboarding"; }
    @GetMapping("/mock/setup")      public String mockSetup() { return "view/mock-setup"; }

    @GetMapping("/mock/session/{id}")
    public String mockSession(@PathVariable Long id, Model model){
        model.addAttribute("session", sessionService.get(id));
        return "view/mock-session";
    }

    @GetMapping("/analysis/{sessionId}")
    public String analysis(@PathVariable Long sessionId, Model model){
        model.addAttribute("sessionId", sessionId);
        return "view/analysis";
    }

    @GetMapping("/reports")         public String reports(){ return "view/reports"; }

    @GetMapping("/reports/{sessionId}")
    public String reportDetail(@PathVariable Long sessionId, Model model){
        model.addAttribute("sessionId", sessionId);
        return "view/report-detail";
    }
}
