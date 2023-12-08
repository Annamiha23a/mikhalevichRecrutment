package com.example.RecruitmentService.service.impl;


import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Vacancy;
import com.example.RecruitmentService.repository.FirmRepository;
import com.example.RecruitmentService.repository.VacancyRepository;
import com.example.RecruitmentService.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class VacancyServiceImpl implements VacancyService {


    private VacancyRepository vacancyRepository;
    private FirmRepository firmRepository;

    @Autowired
    public VacancyServiceImpl(VacancyRepository vacancyRepository, FirmRepository firmRepository){
        this.vacancyRepository=vacancyRepository;
        this.firmRepository=firmRepository;
    }
    @Override
    public Vacancy findById(int id) {
        return vacancyRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException());
    }
    @Override
    public void saveVacancy(Vacancy vacancy, String position,  String requirements,  String responsibilities,  Integer salary, String conditions, String keySkills, Firm firm) {
        Vacancy vacancyFromDb = vacancyRepository.save(vacancy);
        vacancyFromDb.setFirm(firm);
        vacancyRepository.save(vacancyFromDb);
    }
    @Override
    public List<Vacancy> listVacancy(String position){
        if(position!= null) return vacancyRepository.findByPositionContaining(position);
        return vacancyRepository.findAll();
    }

    @Override
    public List<Vacancy> listVacancy(){
        return vacancyRepository.findAll();
    }
    @Override
    public void update(Integer id_vacancy, String position, String requirements, String responsibilities, Integer salary, String conditions, String keySkills){
        Vacancy vacancy = vacancyRepository.findById(id_vacancy).orElse(null);
        if (vacancy!=null){
            vacancy.setPosition(position);
            vacancy.setRequirements(requirements);
            vacancy.setResponsibilities(responsibilities);
            vacancy.setSalary(salary);
            vacancy.setConditions(conditions);
            vacancy.setKeySkills(keySkills);
        }
        vacancyRepository.save(vacancy);
    }
    @Override
    public void removeVacancy(int id_vacancy){
        Vacancy vacancy = findById(id_vacancy);
        vacancyRepository.delete(vacancy);
    }
}