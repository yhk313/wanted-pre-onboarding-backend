package com.wanted.preonboardingbackend.repository;

import com.wanted.preonboardingbackend.entity.ApplyHistory;
import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import com.wanted.preonboardingbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long> {
    boolean existsByRecruitmentNoticeIdAndUserId(Long recruitmentNoticeId, Long userId);
}
