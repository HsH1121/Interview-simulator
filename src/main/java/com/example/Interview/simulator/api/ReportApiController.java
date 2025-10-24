package com.example.Interview.simulator.api;

import com.example.Interview.simulator.dto.ReportDto;
import com.example.Interview.simulator.entity.Report;
import com.example.Interview.simulator.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportApiController {
    private final ReportService svc;

    @PostMapping("/{sessionId}") public Report upsert(@PathVariable Long sessionId, @RequestBody ReportDto dto){
        return svc.createOrUpdate(sessionId, dto);
    }
    @GetMapping("/{sessionId}") public Report bySession(@PathVariable Long sessionId){
        return svc.getBySession(sessionId);
    }
    @GetMapping public List<Report> list(){ return svc.list(); }
}
