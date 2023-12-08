package com.example.RecruitmentService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "applicant")
@Data
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_applicant")
    private Integer id_applicant;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "education")
    private String education;

    @Column(name = "experience")
    private String experience;

    @Column(name = "language")
    private String language;

    @Column(name = "rating")
    private Double rating;

    @OneToOne(cascade = CascadeType.REFRESH, fetch =FetchType.LAZY)
    @JoinColumn(name="id_user")
    private User user;


}
