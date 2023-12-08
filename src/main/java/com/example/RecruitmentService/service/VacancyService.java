package com.example.RecruitmentService.service;
import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Vacancy;

import java.io.IOException;
import java.util.List;

public interface VacancyService {
    Vacancy findById(int id);
    List<Vacancy> listVacancy(String position);
    List<Vacancy> listVacancy();
    void saveVacancy(Vacancy vacancy, String position,  String requirements,  String responsibilities,  Integer salary, String conditions, String keySkills, Firm firm) throws IOException;
    void update( Integer id_vacancy, String position,  String requirements,  String responsibilities,  Integer salary, String conditions, String keySkills, Integer id_firm);
    void removeVacancy(int id_vacancy);
}