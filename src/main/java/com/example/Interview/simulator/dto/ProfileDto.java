package com.example.Interview.simulator.dto;

import com.example.Interview.simulator.entity.Profile;
import com.example.Interview.simulator.entity.Member;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfileDto {
    private String targetRole;
    private String targetCompany;

    public Profile toEntity(Member m){
        return Profile.builder()
                .member(m)
                .targetRole(targetRole)
                .targetCompany(targetCompany)
                .build();
    }
}
