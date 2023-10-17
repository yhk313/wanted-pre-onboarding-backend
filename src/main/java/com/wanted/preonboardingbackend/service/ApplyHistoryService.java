package com.wanted.preonboardingbackend.service;

import com.wanted.preonboardingbackend.dto.ApplyHistoryRequestDto;
import com.wanted.preonboardingbackend.entity.ApplyHistory;
import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import com.wanted.preonboardingbackend.entity.User;
import com.wanted.preonboardingbackend.repository.ApplyHistoryRepository;
import com.wanted.preonboardingbackend.repository.RecruitmentNoticeRepository;
import com.wanted.preonboardingbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplyHistoryService {
    private final ApplyHistoryRepository applyHistoryRepository;
    private final RecruitmentNoticeRepository recruitmentNoticeRepository;
    private final UserRepository userRepository;

    // 사용자 채용공고 지원
    public boolean applyToRecruitment(ApplyHistoryRequestDto applyHistoryRequestDto) {
        Long userId = applyHistoryRequestDto.getUserId();
        Long recruitmentNoticeId = applyHistoryRequestDto.getRecruitmentNoticeId();
        User user = userRepository.findById(userId).get();
        RecruitmentNotice recruitmentNotice = recruitmentNoticeRepository.findById(recruitmentNoticeId).get();

        // 이미 지원한 경우 중복 지원 방지
        if (applyHistoryRepository.existsByRecruitmentNoticeIdAndUserId(recruitmentNoticeId, userId)) {
            return false;
        }

        ApplyHistory applyHistory = new ApplyHistory();
        applyHistory.setRecruitmentNotice(recruitmentNotice);
        applyHistory.setUser(user);

        applyHistoryRepository.save(applyHistory);
        return true;
    }
}
