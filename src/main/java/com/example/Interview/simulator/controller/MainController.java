package com.example.Interview.simulator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Year;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("stats", List.of(
                Map.of("number","10,000+","label","누적 면접 연습"),
                Map.of("number","95%","label","만족도"),
                Map.of("number","4.8","label","평균 평점"),
                Map.of("number","24/7","label","언제든 이용")
        ));

        model.addAttribute("features", List.of(
                Map.of(
                        "title","실전 같은 질문",
                        "description","지원 분야/난이도에 맞춰 즉석 질문을 생성합니다.",
                        "icon","<path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M8 10h.01M12 10h.01M16 10h.01M9 16h6m2 5H7a2 2 0 01-2-2V7a2 2 0 012-2h3l2-2h3a2 2 0 012 2v14a2 2 0 01-2 2z'/>"
                ),
                Map.of(
                        "title","즉각 피드백",
                        "description","답변 길이, 핵심성, 말버릇 등을 정량 평가합니다.",
                        "icon","<path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M11 19l-1.5-1.5M11 5l-1.5 1.5M5 13l1.5-1.5M19 13l-1.5-1.5M8 8l8 8'/>"
                ),
                Map.of(
                        "title","분야별 템플릿",
                        "description","신입/경력/직무별 시나리오가 준비되어 있어요.",
                        "icon","<path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M4 6h16M4 10h16M4 14h10'/>"
                ),
                Map.of(
                        "title","녹음·기록",
                        "description","세션 기록과 개선 히스토리를 자동 저장합니다.",
                        "icon","<path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M12 4v16m8-8H4'/>"
                )
        ));

        // 👇 여기! steps에도 icon을 채워 넣습니다.
        model.addAttribute("steps", List.of(
                Map.of(
                        "step","STEP 1",
                        "title","시나리오 선택",
                        "description","직무/난이도/언어를 고르면 맞춤 면접을 구성합니다.",
                        "showArrow", true,
                        "icon","<path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M4 6h16M4 10h16M4 14h16'/>"
                ),
                Map.of(
                        "step","STEP 2",
                        "title","면접 진행",
                        "description","AI 면접관이 질문하고 음성/텍스트로 응답하세요.",
                        "showArrow", true,
                        "icon","<path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M8 4l8 8-8 8'/>"
                ),
                Map.of(
                        "step","STEP 3",
                        "title","피드백 확인",
                        "description","점수·코멘트·개선 가이드를 바로 받아보세요.",
                        "icon","<path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M12 8v8m-4-4h8'/>"
                )
        ));

        model.addAttribute("testimonials", List.of(
                Map.of("content","실제 면접 같은 긴장감이 있어 많은 도움이 됐어요.","name","김지원","role","백엔드 신입","stars",List.of(1,1,1,1,1)),
                Map.of("content","피드백이 구체적이라 답변이 눈에 띄게 좋아졌습니다.","name","박민수","role","프론트엔드 전환","stars",List.of(1,1,1,1,0)),
                Map.of("content","영어 모의면접도 지원돼서 해외 포지션 준비에 유용해요.","name","이서현","role","데이터 분석","stars",List.of(1,1,1,1,1))
        ));

        model.addAttribute("year", Year.now().toString());
        return "view/main";
    }

}