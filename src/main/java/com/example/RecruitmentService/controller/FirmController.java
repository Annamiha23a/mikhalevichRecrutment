package com.example.RecruitmentService.controller;


import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.service.impl.FirmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping(value = "/firms")
public class FirmController {
    private final FirmServiceImpl firmServiceImpl;

    @GetMapping("/firms")
    public String findAllFirm(Model model, @RequestParam(name="title", required = false) String title) {
        List<Firm> firms=firmServiceImpl.listFirm(title);
        model.addAttribute("firms", firms);
        return "firms";
    }

    @GetMapping("/firms/add")
    public String add(){
        return "addFirm";
    }

    @PostMapping("/firms/add")
    public String addFirm(Firm firm, @RequestParam("title") String title, @RequestParam("field") String field, @RequestParam("year") Integer year, @RequestParam("website") String website,@RequestParam("director") String director, @RequestParam("workPhone") String  workPhone, Model model) throws IOException {
        firmServiceImpl.saveFirm(firm, title, field, year, website, director, workPhone);
        List<Firm> firms=firmServiceImpl.listFirm();
        model.addAttribute("firms", firms);
        return "firms";
    }
}