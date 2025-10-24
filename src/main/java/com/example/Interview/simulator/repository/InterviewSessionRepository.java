package com.example.Interview.simulator.repository;

import com.example.Interview.simulator.entity.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewSessionRepository extends JpaRepository<InterviewSession, Long> {
    List<InterviewSession> findByProfile_IdOrderByCreatedAtDesc(Long profileId);
}
