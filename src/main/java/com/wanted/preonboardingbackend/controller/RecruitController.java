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
    public ResponseEntity createRecruitmentNotice(@RequestBody RecruitmentNoticeRequestDto requestDto) {
        try {
            recruitmentNoticeService.createRecruitmentNotice(requestDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Success");
    }
    // 채용공고 수정
    @PostMapping("api/vi/recruitment-notice/{id}")
    public ResponseEntity updateRecruitmentNotice(@PathVariable Long id, @RequestBody RecruitmentNoticeRequestDto requestDto) {
        try {
            recruitmentNoticeService.updateRecruitmentNotice(id, requestDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Success");
    }
    // 채용공고 삭제
    @DeleteMapping("api/vi/recruitment-notice/{id}")
    public  ResponseEntity deleteRecruitmentNotice(@PathVariable("id") Long id) {
        try {
            recruitmentNoticeService.deleteRecruitmentNotice(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Success");
    }

    // 채용공고 목록 조회
    @GetMapping("api/vi/recruitment-notice/all")
    public ResponseEntity<List<RecruitmentNoticeResponseDto>> getAllRecruitmentNotices() {
        List<RecruitmentNoticeResponseDto> responseDtoList = new ArrayList<>();
        try {
            responseDtoList = recruitmentNoticeService.getAllRecruitmentNotices();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(responseDtoList);
    }

    // 채용공고 검색
    @GetMapping("api/vi/recruitment-notice/url")
    public ResponseEntity<List<RecruitmentNoticeResponseDto>> searchRecruitmentNotice(@RequestParam("search") String search) {
        List<RecruitmentNoticeResponseDto> responseDtoList = new ArrayList<>();

        try {
            responseDtoList = recruitmentNoticeService.searchRecruitment(search);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(responseDtoList);
    }
    // 채용공고 상세 조회
    @GetMapping("api/vi/recruitment-notice/{id}")
    public ResponseEntity<RecruitmentNoticeDetailResponse> getDetailRecruitment(@PathVariable("id") Long id) {
        RecruitmentNoticeDetailResponse detailResponse = null;
        try {
            detailResponse = recruitmentNoticeService.getDetailRecruitment(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(detailResponse);
    }

    @PostMapping("api/vi/recruitment-application")
    public ResponseEntity applyToRecruitment(
            @RequestBody ApplyHistoryRequestDto request) {
        // 채용 공고 ID와 사용자 ID를 사용하여 지원 처리
        try {
            boolean success = applyHistoryService.applyToRecruitment(request);
            if (success) {
                return ResponseEntity.ok("Success!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Success!");
    }

}
