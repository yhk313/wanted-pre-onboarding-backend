package com.wanted.preonboardingbackend.repository;

import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepository extends JpaRepository<RecruitmentNotice,Long> {

}
