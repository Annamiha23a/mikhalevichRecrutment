package com.example.RecruitmentService.service.impl;


import com.example.RecruitmentService.entity.Recruter;
import com.example.RecruitmentService.repository.RecruterRepository;
import com.example.RecruitmentService.service.RecruterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class RecruterServiceImpl implements RecruterService {


    private RecruterRepository recruterRepository;

    @Autowired
    public RecruterServiceImpl(RecruterRepository recruterRepository){
        this.recruterRepository =recruterRepository;
    }
    @Override
    public Recruter findById(int id) {
        return recruterRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException());
    }
    @Override
    public void saveRecruter(Recruter recruter, String specialization,  Integer numberOfHires,  String portfolio,  String searchSpecification) {
        Recruter recruterFromDb = recruterRepository.save(recruter);
    }
    @Override
    public List<Recruter> listRecruter(String specialization){
        if(specialization!= null) return recruterRepository.findBySpecializationContaining(specialization);
        return recruterRepository.findAll();
    }

    @Override
    public List<Recruter> listRecruter(){
        return recruterRepository.findAll();
    }
    @Override
    public void update(Integer id_recruter, String specialization,  Integer numberOfHires,  String portfolio,  String searchSpecification){
        Recruter recruter = recruterRepository.findById(id_recruter).orElse(null);
        if (recruter!=null){
            recruter.setSpecialization(specialization);
            recruter.setNumberOfHires(numberOfHires);
            recruter.setPortfolio(portfolio);
            recruter.setSearchSpecification(searchSpecification);
        }
        recruterRepository.save(recruter);
    }
    @Override
    public void removeRecruter(int id_recruter){
        Recruter recruter = findById(id_recruter);
        recruterRepository.delete(recruter);
    }
}