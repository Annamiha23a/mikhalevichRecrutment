package com.example.RecruitmentService.service;

import com.example.RecruitmentService.entity.Recruter;

import java.io.IOException;
import java.util.List;

public interface RecruterService {
    Recruter findById(int id);
    List<Recruter> listRecruter(String specialization);
    List<Recruter> listRecruter();
    void saveRecruter(Recruter recruter, String specialization,  Integer numberOfHires,  String portfolio,  String searchSpecification) throws IOException;
    void update( Integer id_recruter, String specialization,  Integer numberOfHires,  String portfolio,  String searchSpecification);
    void removeRecruter(int id_recruter);
}
