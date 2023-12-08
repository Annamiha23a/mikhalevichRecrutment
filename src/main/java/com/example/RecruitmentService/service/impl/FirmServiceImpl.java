package com.example.RecruitmentService.service.impl;

import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.repository.FirmRepository;
import com.example.RecruitmentService.repository.RecruterRepository;
import com.example.RecruitmentService.service.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FirmServiceImpl implements FirmService {
    private FirmRepository firmRepository;
    private RecruterRepository recruterRepository;
    @Autowired
    public FirmServiceImpl(FirmRepository firmRepository, RecruterRepository recruterRepository){
        this.firmRepository=firmRepository;
        this.recruterRepository=recruterRepository;
    }
    @Override
    public Firm findById(int id) {
        return firmRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException());
    }
    @Override
    public void saveFirm(Firm firm, String title,  String field,Integer year,  String website, String director, String workPhone) {
        Firm firmFromDb = firmRepository.save(firm);
    }
    @Override
    public List<Firm> listFirm(String title){
        if(title!= null) return firmRepository.findByTitleContaining(title);
        return firmRepository.findAll();
    }

    @Override
    public List<Firm> listFirm(){
        return firmRepository.findAll();
    }
    @Override
    public void update(Integer id_firm, String title, String field, Integer year, String website, String director, String workPhone){
        Firm firm = firmRepository.findById(id_firm).orElse(null);
        if (firm!=null){
            firm.setTitle(title);
            firm.setField(field);
            firm.setYear(year);
            firm.setWebsite(website);
            firm.setDirector(director);
            firm.setWorkPhone(workPhone);
        }
        firmRepository.save(firm);
    }

    @Override
    public void removeFirm(int id_firm) {
        Firm firm = findById(id_firm);
        firmRepository.delete(firm);
    }
    @Override
    public Firm getFirmByUserName (Principal principal){

        if(principal==null) return new Firm();
        String username=principal.getName();
        return firmRepository.findByUserName(username).orElseThrow(()->new NoSuchElementException());
    }

}
