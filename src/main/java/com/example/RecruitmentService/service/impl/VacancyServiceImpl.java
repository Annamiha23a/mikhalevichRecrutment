package com.example.RecruitmentService.service.impl;


import com.example.RecruitmentService.entity.Vacancy;
import com.example.RecruitmentService.repository.VacancyRepository;
import com.example.RecruitmentService.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class VacancyServiceImpl implements VacancyService {


    private VacancyRepository vacancyRepository;

    @Autowired
    public VacancyServiceImpl(VacancyRepository vacancyRepository){
        this.vacancyRepository=vacancyRepository;
    }
    @Override
    public Vacancy findById(int id) {
        return vacancyRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException());
    }
    @Override
    public void saveVacancy(Vacancy vacancy, String position,  String requirements,  String responsibilities,  Integer salary) {
        Vacancy vacancyFromDb = vacancyRepository.save(vacancy);
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
}