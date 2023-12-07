package com.example.RecruitmentService.service;
import com.example.RecruitmentService.entity.Vacancy;

import java.io.IOException;
import java.util.List;

public interface VacancyService {
    Vacancy findById(int id);
    List<Vacancy> listVacancy(String position);
    List<Vacancy> listVacancy();
    void saveVacancy(Vacancy vacancy, String position,  String requirements,  String responsibilities,  Integer salary) throws IOException;
}