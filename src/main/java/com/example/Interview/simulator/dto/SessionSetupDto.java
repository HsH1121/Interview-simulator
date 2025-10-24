package com.example.Interview.simulator.dto;

import com.example.Interview.simulator.entity.InterviewSession;
import com.example.Interview.simulator.entity.Profile;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SessionSetupDto {
    private Long profileId;
    private String company;
    private String jobTitle;
    private String mode;           // TEXT/VOICE/VIDEO
    private Integer questionCount;

    public InterviewSession toEntity(Profile p){
        return InterviewSession.builder()
                .profile(p)
                .company(company)
                .jobTitle(jobTitle)
                .mode(mode)
                .questionCount(questionCount)
                .status("SETUP")
                .build();
    }
}
