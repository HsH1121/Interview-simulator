package com.example.Interview.simulator.service;

import com.example.Interview.simulator.dto.ReportDto;
import com.example.Interview.simulator.entity.InterviewSession;
import com.example.Interview.simulator.entity.Report;
import com.example.Interview.simulator.repository.InterviewSessionRepository;
import com.example.Interview.simulator.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ReportService {
    private final ReportRepository repo;
    private final InterviewSessionRepository sessionRepo;

    public Report createOrUpdate(Long sessionId, ReportDto dto){
        InterviewSession s = sessionRepo.findById(sessionId).orElseThrow();
        return repo.findBySession_Id(sessionId)
                .map(r -> {
                    r.setTotalScore(dto.getTotalScore());
                    r.setStrengths(dto.getStrengths());
                    r.setImprovements(dto.getImprovements());
                    return repo.save(r);
                })
                .orElseGet(() -> repo.save(dto.toEntity(s)));
    }

    public Report getBySession(Long sessionId){ return repo.findBySession_Id(sessionId).orElseThrow(); }
    public List<Report> list(){ return repo.findAll(); }
}
