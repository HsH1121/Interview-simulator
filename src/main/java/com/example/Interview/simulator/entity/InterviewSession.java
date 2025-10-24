package com.example.Interview.simulator.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interview_session")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InterviewSession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 세션은 프로필(=회원)에 종속
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(length = 100) private String company;
    @Column(length = 100) private String jobTitle;
    @Column(length = 16)  private String mode;           // TEXT/VOICE/VIDEO
    private Integer questionCount;

    @Column(length = 16)
    private String status; // SETUP / RUNNING / DONE

    private LocalDateTime createdAt;
    @PrePersist void onCreate(){ this.createdAt = LocalDateTime.now(); }
}
