package com.wanted.preonboardingbackend.dto;

import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentNoticeResponseDto {
    private Long recruitmentNoticeId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Long compensation;
    private String technologyUsed;

}
