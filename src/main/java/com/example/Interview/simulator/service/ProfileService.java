package com.example.Interview.simulator.service;

import com.example.Interview.simulator.dto.ProfileDto;
import com.example.Interview.simulator.entity.Member;
import com.example.Interview.simulator.entity.Profile;
import com.example.Interview.simulator.repository.MemberRepository;
import com.example.Interview.simulator.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepo;
    private final MemberRepository memberRepo;

    // 로그인 회원 기준 1:1 프로필 생성/수정 단순화
    public Profile upsertForMember(Long memberId, ProfileDto dto){
        Member m = memberRepo.findById(memberId).orElseThrow();
        return profileRepo.findByMember(m)
                .map(p -> {
                    p.setTargetRole(dto.getTargetRole());
                    p.setTargetCompany(dto.getTargetCompany());
                    return profileRepo.save(p);
                })
                .orElseGet(() -> profileRepo.save(dto.toEntity(m)));
    }

    public Profile getByMember(Long memberId){
        Member m = memberRepo.findById(memberId).orElseThrow();
        return profileRepo.findByMember(m).orElseThrow();
    }

    public Profile get(Long id){ return profileRepo.findById(id).orElseThrow(); }
}
