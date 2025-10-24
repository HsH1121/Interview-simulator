package com.example.Interview.simulator.repository;

import com.example.Interview.simulator.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findBySession_Id(Long sessionId);
}
