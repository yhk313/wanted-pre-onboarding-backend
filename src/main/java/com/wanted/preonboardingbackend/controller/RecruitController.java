package com.wanted.preonboardingbackend.controller;

import com.wanted.preonboardingbackend.dto.ApplyHistoryRequestDto;
import com.wanted.preonboardingbackend.dto.RecruitmentNoticeDetailResponse;
import com.wanted.preonboardingbackend.dto.RecruitmentNoticeRequestDto;
import com.wanted.preonboardingbackend.dto.RecruitmentNoticeResponseDto;
import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import com.wanted.preonboardingbackend.service.ApplyHistoryService;
import com.wanted.preonboardingbackend.service.RecruitmentNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitmentNoticeService recruitmentNoticeService;
    private final ApplyHistoryService applyHistoryService;
    // 채용공고 등록
    @PostMapping("api/vi/recruitment-notice")
    public ResponseEntity<String> createRecruitmentNotice(@RequestBody RecruitmentNoticeRequestDto requestDto) {
        return recruitmentNoticeService.createRecruitmentNotice(requestDto);
    }
    // 채용공고 수정
    @PostMapping("api/vi/recruitment-notice/{id}")
    public ResponseEntity<String> updateRecruitmentNotice(@PathVariable Long id, @RequestBody RecruitmentNoticeRequestDto requestDto) {
        return recruitmentNoticeService.updateRecruitmentNotice(id, requestDto);
    }
    // 채용공고 삭제
    @DeleteMapping("api/vi/recruitment-notice/{id}")
    public  ResponseEntity<String> deleteRecruitmentNotice(@PathVariable("id") Long id) {
        return recruitmentNoticeService.deleteRecruitmentNotice(id);
    }
    // 채용공고 목록 조회
    @GetMapping("api/vi/recruitment-notice/all")
    public ResponseEntity<List<RecruitmentNoticeResponseDto>> getAllRecruitmentNotices() {
        return recruitmentNoticeService.getAllRecruitmentNotices();
    }

    // 채용공고 검색
    @GetMapping("api/vi/recruitment-notice/url")
    public ResponseEntity<List<RecruitmentNoticeResponseDto>> searchRecruitmentNotice(@RequestParam("search") String search) {
        return recruitmentNoticeService.searchRecruitment(search);
    }
    // 채용공고 상세 조회
    @GetMapping("api/vi/recruitment-notice/{id}")
    public ResponseEntity<RecruitmentNoticeDetailResponse> getDetailRecruitment(@PathVariable("id") Long id) {
        return recruitmentNoticeService.getDetailRecruitment(id);
    }

    @PostMapping("api/vi/recruitment-application")
    public ResponseEntity<String> applyToRecruitment(
            @RequestBody ApplyHistoryRequestDto request) {
        // 채용 공고 ID와 사용자 ID를 사용하여 지원 처리
        boolean success = applyHistoryService.applyToRecruitment(request);

        if (success) {
            return ResponseEntity.ok("Application submitted successfully.");
        } else {
            throw new IllegalArgumentException("Recruitment not found");
        }
    }

}
