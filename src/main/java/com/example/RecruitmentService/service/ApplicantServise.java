package com.example.RecruitmentService.service;

import com.example.RecruitmentService.entity.Applicant;

import java.io.IOException;
import java.util.List;

public interface ApplicantServise {
    Applicant findById(int id);
    List<Applicant> listApplicant(String specialization);
    List<Applicant> listApplicant();
    void saveApplicant(Applicant applicant, String specialization,  String education,  String experience,  String language, Double rating) throws IOException;
    void update( Integer id_applicant, String specialization,  String education,  String experience,  String language, Double rating);
    void updateRating( Integer id_applicant, Double rating);
    void removeApplicant(int id_applicant);
}
