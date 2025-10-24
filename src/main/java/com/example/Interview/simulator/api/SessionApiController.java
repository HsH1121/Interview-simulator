package com.example.Interview.simulator.api;

import com.example.Interview.simulator.dto.SessionSetupDto;
import com.example.Interview.simulator.entity.InterviewSession;
import com.example.Interview.simulator.service.InterviewSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class SessionApiController {
    private final InterviewSessionService svc;

    @PostMapping("/setup")     public InterviewSession setup(@RequestBody SessionSetupDto dto){ return svc.createSetup(dto); }
    @PostMapping("/{id}/start")  public InterviewSession start(@PathVariable Long id){ return svc.start(id); }
    @PostMapping("/{id}/finish") public InterviewSession finish(@PathVariable Long id){ return svc.finish(id); }
    @GetMapping("/{id}")        public InterviewSession get(@PathVariable Long id){ return svc.get(id); }
    @GetMapping("/profile/{profileId}") public List<InterviewSession> byProfile(@PathVariable Long profileId){
        return svc.listByProfile(profileId);
    }
}
