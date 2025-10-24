package com.example.Interview.simulator.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false, unique = true)
    private InterviewSession session;

    private Double totalScore;
    @Column(length = 2000) private String strengths;
    @Column(length = 2000) private String improvements;

    private LocalDateTime createdAt;
    @PrePersist void onCreate(){ this.createdAt = LocalDateTime.now(); }
}
