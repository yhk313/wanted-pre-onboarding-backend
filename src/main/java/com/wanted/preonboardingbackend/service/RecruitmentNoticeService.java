package com.wanted.preonboardingbackend.service;

import com.wanted.preonboardingbackend.dto.RecruitmentNoticeRequestDto;
import com.wanted.preonboardingbackend.entity.Company;
import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import com.wanted.preonboardingbackend.repository.CompanyRepository;
import com.wanted.preonboardingbackend.repository.RecruitmentNoticeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
