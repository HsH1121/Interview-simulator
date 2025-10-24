package com.example.Interview.simulator.service;

import com.example.Interview.simulator.dto.SessionSetupDto;
import com.example.Interview.simulator.entity.InterviewSession;
import com.example.Interview.simulator.entity.Profile;
import com.example.Interview.simulator.repository.InterviewSessionRepository;
import com.example.Interview.simulator.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class InterviewSessionService {
    private final InterviewSessionRepository repo;
    private final ProfileRepository profileRepo;

    public InterviewSession createSetup(SessionSetupDto dto){
        Profile p = profileRepo.findById(dto.getProfileId()).orElseThrow();
        return repo.save(dto.toEntity(p));
    }

    public InterviewSession start(Long sessionId){
        InterviewSession s = get(sessionId);
        s.setStatus("RUNNING");
        return repo.save(s);
    }

    public InterviewSession finish(Long sessionId){
        InterviewSession s = get(sessionId);
        s.setStatus("DONE");
        return repo.save(s);
    }

    public InterviewSession get(Long id){ return repo.findById(id).orElseThrow(); }

    public List<InterviewSession> listByProfile(Long profileId){
        return repo.findByProfile_IdOrderByCreatedAtDesc(profileId);
    }
}
