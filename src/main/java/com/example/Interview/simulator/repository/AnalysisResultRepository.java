package com.example.Interview.simulator.repository;

import com.example.Interview.simulator.entity.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {
    Optional<AnalysisResult> findBySession_Id(Long sessionId);
}
