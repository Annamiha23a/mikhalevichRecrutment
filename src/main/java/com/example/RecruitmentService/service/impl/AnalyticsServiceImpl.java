package com.example.RecruitmentService.service.impl;

import com.example.RecruitmentService.entity.*;
import com.example.RecruitmentService.repository.*;
import com.example.RecruitmentService.service.AnalyticsService;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    private UserRepository userRepository;

    private ApplicantRepository applicantRepository;
    private FirmRepository firmRepository;
    private RecruterRepository recruterRepository;
    private ResponseRepository responseRepository;
    private VacancyRepository vacancyRepository;
    @Autowired
    public AnalyticsServiceImpl(UserRepository userRepository,   ApplicantRepository applicantRepository, FirmRepository firmRepository, RecruterRepository recruterRepository, ResponseRepository responseRepository, VacancyRepository vacancyRepository) {
        this.userRepository = userRepository;
        this.applicantRepository=applicantRepository;
        this.firmRepository=firmRepository;
        this.recruterRepository=recruterRepository;
        this.responseRepository=responseRepository;
        this.vacancyRepository=vacancyRepository;
    }


    public int userSize(){
        List<User> users = userRepository.findAll();
        return (users == null || users.isEmpty()) ? 0 : users.size()+1;
    }
    public int applicantSize() {
        List<Applicant> applicants = applicantRepository.findAll();
        return (applicants == null || applicants.isEmpty()) ? 0 : applicants.size();
    }

    public int firmSize() {
        List<Firm> firms = firmRepository.findAll();
        return (firms == null || firms.isEmpty()) ? 0 : firms.size();
    }
    public List<Firm> firmAll() {
        List<Firm> firms = firmRepository.findAll();
        return (firms == null || firms.isEmpty()) ? null : firms;
    }

    public int recruterSize() {
        List<Recruter> recruters = recruterRepository.findAll();
        return (recruters == null || recruters.isEmpty()) ? 0 : recruters.size();
    }

    public int responseSize() {
        List<Response> responses = responseRepository.findAll();
        return (responses == null || responses.isEmpty()) ? 0 : responses.size();
    }
    public List<Response> responseAll(){
        return responseRepository.findAll();
    }
    public int vacancySize() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return (vacancies == null || vacancies.isEmpty()) ? 0 : vacancies.size();
    }
    public List<Vacancy> vacancyAll(){
        return vacancyRepository.findAll();
    }



}
