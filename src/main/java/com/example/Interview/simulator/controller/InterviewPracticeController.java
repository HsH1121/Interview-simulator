package com.example.Interview.simulator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interview-practice")
public class InterviewPracticeController {

    // GET /interview-practice -> templates/interview-practice/interview-practice.mustache
    @GetMapping
    public String showInterviewPracticePage() {
        return "interview-practice/interview-practice";
    }
}
