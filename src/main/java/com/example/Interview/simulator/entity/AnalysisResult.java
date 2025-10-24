package com.example.Interview.simulator.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_result")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AnalysisResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false, unique = true)
    private InterviewSession session;

    private Double audioScore;   // 음성
    private Double textScore;    // 텍스트
    private Double visionScore;  // 비언어
    @Column(length = 2000) private String notes;

    private LocalDateTime createdAt;
    @PrePersist void onCreate(){ this.createdAt = LocalDateTime.now(); }
}
