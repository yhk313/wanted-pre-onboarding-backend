package com.wanted.preonboardingbackend.service;

import com.wanted.preonboardingbackend.dto.*;
import com.wanted.preonboardingbackend.entity.Company;
import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import com.wanted.preonboardingbackend.repository.CompanyRepository;
import com.wanted.preonboardingbackend.repository.RecruitmentNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitmentNoticeService {
    private final RecruitmentNoticeRepository recruitmentNoticeRepository;
    private final CompanyRepository companyRepository;

    public ResponseEntity<String> createRecruitmentNotice(RecruitmentNoticeRequestDto requestDto) {
        Optional<Company> findCompany = companyRepository.findById(requestDto.getCompanyId());

        //  채용공고 등록
        if (findCompany.isPresent()) {
            RecruitmentNotice recruitmentNotice = RecruitmentNotice.builder()
                    .company(findCompany.get())
                    .position(requestDto.getPosition())
                    .compensation(requestDto.getCompensation())
                    .details(requestDto.getDetails())
                    .technologyUsed(requestDto.getTechnologyUsed())
                    .build();
            recruitmentNoticeRepository.save(recruitmentNotice);
            return ResponseEntity.ok("Success!");
        } else {
            throw new IllegalArgumentException("Recruitment not found");
        }

    }

    // 채용공고 수정
    public ResponseEntity<String> updateRecruitmentNotice(Long id , RecruitmentNoticeRequestDto requestDto) {
        Optional<RecruitmentNotice> findRecruitment = recruitmentNoticeRepository.findById(id);
        if(findRecruitment.isPresent()) {
            RecruitmentNotice recruitmentNotice = findRecruitment.get();
            recruitmentNotice.setDetails(requestDto.getDetails());
            recruitmentNotice.setCompensation(requestDto.getCompensation());
            recruitmentNotice.setPosition(requestDto.getPosition());
            recruitmentNotice.setTechnologyUsed(requestDto.getTechnologyUsed());

            recruitmentNoticeRepository.save(recruitmentNotice);
            return ResponseEntity.ok("Success!");
        } else {
            throw new IllegalArgumentException("Recruitment not found");
        }
    }

    // 채용공고 삭제
    public ResponseEntity<String> deleteRecruitmentNotice(Long id) {
        Optional<RecruitmentNotice> findRecruitment = recruitmentNoticeRepository.findById(id);
        if(findRecruitment.isPresent()) {
            recruitmentNoticeRepository.delete(findRecruitment.get());
            return ResponseEntity.ok("Success");
        } else {
            throw new IllegalArgumentException("Recruitment not found");
        }
    }

    // 채용공고 목록 조회
    public ResponseEntity<List<RecruitmentNoticeResponseDto>> getAllRecruitmentNotices() {
        List<RecruitmentNotice> recruitmentNoticeList = recruitmentNoticeRepository.findAll();
        List<RecruitmentNoticeResponseDto> responseDtoList = new ArrayList<>();

        for (RecruitmentNotice recruitmentNotice : recruitmentNoticeList) {
            RecruitmentNoticeResponseDto dto = new RecruitmentNoticeResponseDto();
            dto.setRecruitmentNoticeId(recruitmentNotice.getId());
            dto.setCompanyName(recruitmentNotice.getCompany().getName());
            dto.setCountry(recruitmentNotice.getCompany().getCountry());
            dto.setRegion(recruitmentNotice.getCompany().getRegion());
            dto.setPosition(recruitmentNotice.getPosition());
            dto.setCompensation(recruitmentNotice.getCompensation());
            dto.setTechnologyUsed(recruitmentNotice.getTechnologyUsed());
            responseDtoList.add(dto);
        }

        return ResponseEntity.ok(responseDtoList);
    }
    // 채용공고 검색(회사명, 포지션, 사용기술)
    public ResponseEntity<List<RecruitmentNoticeResponseDto>> searchRecruitment(String search) {
        List<RecruitmentNotice> recruitmentNoticeList = recruitmentNoticeRepository.findByCompanyNameContainingOrPositionContainingOrTechnologyUsedContaining(search, search, search);
        List<RecruitmentNoticeResponseDto> responseDtoList = new ArrayList<>();

        for (RecruitmentNotice recruitmentNotice : recruitmentNoticeList) {
            RecruitmentNoticeResponseDto dto = new RecruitmentNoticeResponseDto();
            dto.setRecruitmentNoticeId(recruitmentNotice.getId());
            dto.setCompanyName(recruitmentNotice.getCompany().getName());
            dto.setCountry(recruitmentNotice.getCompany().getCountry());
            dto.setRegion(recruitmentNotice.getCompany().getRegion());
            dto.setPosition(recruitmentNotice.getPosition());
            dto.setCompensation(recruitmentNotice.getCompensation());
            dto.setTechnologyUsed(recruitmentNotice.getTechnologyUsed());
            responseDtoList.add(dto);
        }

        return ResponseEntity.ok(responseDtoList);
    }

    // 채용공고 상세페이지
    public ResponseEntity<RecruitmentNoticeDetailResponse> getDetailRecruitment(Long id) {
        Optional<RecruitmentNotice> findRecruit = recruitmentNoticeRepository.findById(id);
        if(findRecruit.isPresent()) {
            RecruitmentNotice recruitmentNotice = findRecruit.get();
            Long companyId = recruitmentNotice.getCompany().getId();
            List<RecruitmentNotice> findRecruitByCompanyId = recruitmentNoticeRepository.findByCompanyId(companyId);
            RecruitmentNoticeDetailResponse dto = new RecruitmentNoticeDetailResponse();
            dto.setRecruitmentNoticeId(recruitmentNotice.getId());
            dto.setCompanyName(recruitmentNotice.getCompany().getName());
            dto.setCountry(recruitmentNotice.getCompany().getCountry());
            dto.setRegion(recruitmentNotice.getCompany().getRegion());
            dto.setPosition(recruitmentNotice.getPosition());
            dto.setCompensation(recruitmentNotice.getCompensation());
            dto.setDetails(recruitmentNotice.getDetails());
            dto.setTechnologyUsed(recruitmentNotice.getTechnologyUsed());
            List<Long> recruitmentIdList = new ArrayList<>();
            // 다른 채용 공고 ID를 리스트에 추가
            for (RecruitmentNotice otherRecruitment : findRecruitByCompanyId) {
                if(!otherRecruitment.getId().equals(id))
                recruitmentIdList.add(otherRecruitment.getId());
            }
            dto.setOtherRecruitmentNotices(recruitmentIdList);

            return ResponseEntity.ok(dto);
        } else {
            throw new IllegalArgumentException("Recruitment not found");
        }
    }

}
