package com.example.RecruitmentService.service;

import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Vacancy;

import java.io.IOException;
import java.util.List;

public interface FirmService {
    Firm findById(int id);
    List<Firm> listFirm(String title);
    List<Firm> listFirm();
    void saveFirm(Firm firm, String title,  String field, Integer year, String website, String director, String workPhone) throws IOException;
}
