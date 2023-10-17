package com.wanted.preonboardingbackend.repository;

import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentNoticeRepository extends JpaRepository<RecruitmentNotice,Long> {
    List<RecruitmentNotice> findByCompanyNameContainingOrPositionContainingOrTechnologyUsedContaining(String company, String position, String technology);
    List<RecruitmentNotice> findByCompanyId(Long companyId);
}
