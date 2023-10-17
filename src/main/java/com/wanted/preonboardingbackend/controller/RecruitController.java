package com.wanted.preonboardingbackend.controller;

import com.wanted.preonboardingbackend.dto.RecruitmentNoticeRequestDto;
import com.wanted.preonboardingbackend.dto.RecruitmentNoticeResponseDto;
import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
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


}
