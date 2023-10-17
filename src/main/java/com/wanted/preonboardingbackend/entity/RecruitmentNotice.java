package com.wanted.preonboardingbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "recruitment_notice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentNotice {
    @Id
    @Column(name = "recruitment_notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonManagedReference
    private Company company;

    @Column
    private String position;
    @Column
    private Long compensation;
    @Column
    private String details;
    @Column
    private String technologyUsed;
}
