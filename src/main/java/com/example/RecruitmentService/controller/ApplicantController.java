package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.entity.Recruter;
import com.example.RecruitmentService.service.impl.ApplicantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/applicants")
public class ApplicantController {
    private final ApplicantServiceImpl applicantServiceImpl;

    @GetMapping("")
    public String findAllApplicant(Model model, @RequestParam(name="specialization", required = false) String specialization ){
        List<Applicant> applicants= applicantServiceImpl.listApplicant(specialization);
        model.addAttribute("applicants", applicants);
        return "applicants";
    }

    @GetMapping("/add")
    public String addApplic(Model model){
//        List<User> users = userServiceImpl.listFirm();
//        model.addAttribute("users", users);
        return "applicantAdd";
    }

    @GetMapping("/{id}")
    public String findApplicant(@PathVariable("id") Integer id, Model model) {
        Applicant applicant= applicantServiceImpl.findById(id);
        model.addAttribute("applicant", applicant);
        return "applicant-details";
    }

    @GetMapping("/{id}/update")
    public String updateApplicant(@PathVariable(value = "id") Integer id, Model model) {
        Applicant applicant = applicantServiceImpl.findById(id);
        model.addAttribute("applicant",applicant);
        return "applicantUpdate";
    }

    @GetMapping("/calculation")
    public String calculationRating(Model model){
        return "calculation";
    }


    @PostMapping("/add")
    public String addApplicant(Applicant applicant, @RequestParam String specialization, @RequestParam String education, @RequestParam String experience, @RequestParam String language,@RequestParam Double rating, Model model) throws IOException {
        applicantServiceImpl.saveApplicant(applicant, specialization, education, experience, language, rating);
        List<Applicant> applicants=applicantServiceImpl.listApplicant();
        model.addAttribute("applicants", applicants);
        return "redirect:/applicants";
    }

    @PostMapping("/{id}/update")
    public String postUpdateApplicant(@PathVariable Integer id,@RequestParam String specialization, @RequestParam String education, @RequestParam String experience, @RequestParam String language, @RequestParam Double rating){
        applicantServiceImpl.update(id,specialization, education, experience, language, rating);
        return "redirect:/applicants";
    }


    @PostMapping("/{id}/remove")
    public String removeApplicant(@PathVariable(value = "id") Integer id, Model model) {
        applicantServiceImpl.removeApplicant(id);
        return "redirect:/applicants";
    }
}
