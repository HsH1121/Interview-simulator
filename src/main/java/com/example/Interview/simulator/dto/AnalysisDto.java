package com.example.Interview.simulator.dto;

import com.example.Interview.simulator.entity.AnalysisResult;
import com.example.Interview.simulator.entity.InterviewSession;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AnalysisDto {
    private Double audioScore;
    private Double textScore;
    private Double visionScore;
    private String notes;

    public AnalysisResult toEntity(InterviewSession s){
        return AnalysisResult.builder()
                .session(s)
                .audioScore(audioScore)
                .textScore(textScore)
                .visionScore(visionScore)
                .notes(notes)
                .build();
    }
}
