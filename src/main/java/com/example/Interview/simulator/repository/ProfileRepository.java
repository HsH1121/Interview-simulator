package com.example.Interview.simulator.repository;

import com.example.Interview.simulator.entity.Profile;
import com.example.Interview.simulator.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByMember(Member member);
}
