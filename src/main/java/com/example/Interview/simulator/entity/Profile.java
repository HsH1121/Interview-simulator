package com.example.Interview.simulator.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 회원 1:1 프로필
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;

    @Column(length = 100)
    private String targetRole;      // 희망 직무
    @Column(length = 100)
    private String targetCompany;   // 관심 기업

    private LocalDateTime createdAt;

    @PrePersist void onCreate(){ this.createdAt = LocalDateTime.now(); }
}
