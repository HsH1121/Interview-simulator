package com.example.Interview.simulator.dto;

import com.example.Interview.simulator.entity.InterviewSession;
import com.example.Interview.simulator.entity.Report;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReportDto {
    private Double totalScore;
    private String strengths;
    private String improvements;

    public Report toEntity(InterviewSession s){
        return Report.builder()
                .session(s)
                .totalScore(totalScore)
                .strengths(strengths)
                .improvements(improvements)
                .build();
    }
}
