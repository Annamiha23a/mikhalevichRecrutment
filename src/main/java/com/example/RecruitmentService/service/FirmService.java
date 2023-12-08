package com.example.RecruitmentService.service;

import com.example.RecruitmentService.entity.Firm;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface FirmService {
    Firm findById(int id);
    List<Firm> listFirm(String title);
    List<Firm> listFirm();
    void saveFirm(Firm firm, String title,  String field, Integer year, String website, String director, String workPhone) throws IOException;
    void update(Integer id_firm, String title, String field, Integer year, String website, String director, String workPhone);
    void removeFirm(int id_firm);
    Firm getFirmByUserName (Principal principal);
}
