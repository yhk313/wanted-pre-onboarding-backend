package com.wanted.preonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentNoticeDetailResponse {
    private Long recruitmentNoticeId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Long compensation;
    private String details;
    private String technologyUsed;
    private List<Long> otherRecruitmentNotices;
}
