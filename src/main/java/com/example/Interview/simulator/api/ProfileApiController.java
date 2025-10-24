package com.example.Interview.simulator.api;

import com.example.Interview.simulator.dto.ProfileDto;
import com.example.Interview.simulator.entity.Profile;
import com.example.Interview.simulator.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileApiController {
    private final ProfileService svc;

    // 로그인된 memberId를 프론트에서 전달한다고 가정 (추후 SecurityContext로 대체 가능)
    @PostMapping("/{memberId}") public Profile upsert(@PathVariable Long memberId, @RequestBody ProfileDto dto){
        return svc.upsertForMember(memberId, dto);
    }
    @GetMapping("/{memberId}") public Profile getByMember(@PathVariable Long memberId){
        return svc.getByMember(memberId);
    }
}
