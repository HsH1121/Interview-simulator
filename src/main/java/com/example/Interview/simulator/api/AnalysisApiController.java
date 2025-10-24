package com.example.Interview.simulator.api;

import com.example.Interview.simulator.dto.AnalysisDto;
import com.example.Interview.simulator.entity.AnalysisResult;
import com.example.Interview.simulator.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/analysis")
public class AnalysisApiController {
    private final AnalysisService svc;

    @PostMapping("/{sessionId}") public AnalysisResult upsert(@PathVariable Long sessionId, @RequestBody AnalysisDto dto){
        return svc.upsert(sessionId, dto);
    }
    @GetMapping("/{sessionId}") public AnalysisResult get(@PathVariable Long sessionId){
        return svc.getBySession(sessionId);
    }
}
