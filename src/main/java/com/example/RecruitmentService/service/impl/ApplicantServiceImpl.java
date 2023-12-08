package com.example.RecruitmentService.service.impl;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.repository.ApplicantRepository;
import com.example.RecruitmentService.service.ApplicantServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApplicantServiceImpl implements ApplicantServise {
    private ApplicantRepository applicantRepository;
    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository){
        this.applicantRepository=applicantRepository;
    }
    @Override
    public Applicant findById(int id) {
        return applicantRepository.findById(id).orElseThrow(()->new NoSuchElementException());
    }

    @Override
    public List<Applicant> listApplicant(String specialization) {
        if(specialization!= null) return applicantRepository.findBySpecializationContaining(specialization);
        return applicantRepository.findAll();
    }

    @Override
    public List<Applicant> listApplicant() {
        return applicantRepository.findAll();
    }

    @Override
    public void saveApplicant(Applicant applicant, String specialization, String education, String experience, String language, Double rating) throws IOException {
        Applicant applicantDB=applicantRepository.save(applicant);
    }


    @Override
    public void update(Integer id_applicant, String specialization, String education, String experience, String language, Double rating) {
        Applicant applicant=applicantRepository.findById(id_applicant).orElse(null);
        if(applicant!=null){
            applicant.setSpecialization(specialization);
            applicant.setEducation(education);
            applicant.setExperience(experience);
            applicant.setLanguage(language);
            applicant.setRating(rating);
        }
        applicantRepository.save(applicant);
    }

    @Override
    public void updateRating(Integer id_applicant, Double rating) {
        Applicant applicant=applicantRepository.findById(id_applicant).orElse(null);
        if(applicant!=null){
            applicant.setRating(rating);
        }
        applicantRepository.save(applicant);
    }

    @Override
    public void removeApplicant(int id_applicant) {
        Applicant applicant=findById(id_applicant);
        applicantRepository.delete(applicant);
    }
}
