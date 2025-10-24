package com.example.Interview.simulator.service;

import com.example.Interview.simulator.dto.AnalysisDto;
import com.example.Interview.simulator.entity.AnalysisResult;
import com.example.Interview.simulator.entity.InterviewSession;
import com.example.Interview.simulator.repository.AnalysisResultRepository;
import com.example.Interview.simulator.repository.InterviewSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AnalysisService {
    private final AnalysisResultRepository repo;
    private final InterviewSessionRepository sessionRepo;

    public AnalysisResult upsert(Long sessionId, AnalysisDto dto){
        InterviewSession s = sessionRepo.findById(sessionId).orElseThrow();
        return repo.findBySession_Id(sessionId)
                .map(ar -> {
                    ar.setAudioScore(dto.getAudioScore());
                    ar.setTextScore(dto.getTextScore());
                    ar.setVisionScore(dto.getVisionScore());
                    ar.setNotes(dto.getNotes());
                    return repo.save(ar);
                })
                .orElseGet(() -> repo.save(dto.toEntity(s)));
    }

    public AnalysisResult getBySession(Long sessionId){
        return repo.findBySession_Id(sessionId).orElseThrow();
    }
}
