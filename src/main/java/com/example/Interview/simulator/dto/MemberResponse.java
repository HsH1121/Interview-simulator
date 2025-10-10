package com.example.Interview.simulator.dto;

import com.example.Interview.simulator.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;

    public static MemberResponse from(Member m) {
        return new MemberResponse(m.getId(), m.getEmail(), m.getNickname(), m.getCreatedAt());
    }
}
