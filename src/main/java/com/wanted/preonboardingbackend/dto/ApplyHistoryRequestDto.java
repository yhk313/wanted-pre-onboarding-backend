package com.wanted.preonboardingbackend.dto;

import com.wanted.preonboardingbackend.entity.RecruitmentNotice;
import com.wanted.preonboardingbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyHistoryRequestDto {
    private Long userId;
    private Long recruitmentNoticeId;
}
