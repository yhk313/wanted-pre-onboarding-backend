package com.wanted.preonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentNoticeRequestDto {
    private Long companyId;
    private Long recruitmentNoticeId;
    private String position;
    private Long compensation;
    private String details;
    private String technologyUsed;

}
