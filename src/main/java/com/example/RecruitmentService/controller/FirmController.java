package com.example.RecruitmentService.controller;


import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.User;
import com.example.RecruitmentService.service.impl.FirmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping("/firms/{id_firm}")
    public String findFirm(@PathVariable("id_firm") Integer id_firm, Model model) {
        Firm firm= firmServiceImpl.findById(id_firm);
        model.addAttribute("firm", firm);
        return "firm-details";
    }

    @GetMapping("/firms/{id_firm}/update")
    public String updateFirm(@PathVariable(value = "id_firm") int id_firm, Model model) {
        Firm firm = firmServiceImpl.findById(id_firm);
        model.addAttribute("firm",firm);
        return "firmUpdate";
    }

    @PostMapping("/firms/add")
    public String addFirm(Firm firm, @RequestParam("title") String title, @RequestParam("field") String field, @RequestParam("year") Integer year, @RequestParam("website") String website,@RequestParam("director") String director, @RequestParam("workPhone") String  workPhone, Model model) throws IOException {
        firmServiceImpl.saveFirm(firm, title, field, year, website, director, workPhone);
        List<Firm> firms=firmServiceImpl.listFirm();
        model.addAttribute("firms", firms);
        return "redirect:/firms";
    }

    @PostMapping("/firms/{id_firm}/update")
    public String postUpdateFirm(@PathVariable Integer id_firm,@RequestParam String title, @RequestParam String field, @RequestParam Integer year, @RequestParam String website, @RequestParam String director, @RequestParam String workPhone){
        firmServiceImpl.update(id_firm,title, field, year, website, director, workPhone);
        return "redirect:/firms";
    }


    @PostMapping("/firms/{id_firm}/remove")
    public String removeFirm(@PathVariable(value = "id_firm") Integer id_firm, Model model) {
        firmServiceImpl.removeFirm(id_firm);
        return "redirect:/firms";
    }
}