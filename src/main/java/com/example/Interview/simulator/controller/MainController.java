package com.example.Interview.simulator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Year;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("year", Year.now().toString());
        return "view/main";
    }
}