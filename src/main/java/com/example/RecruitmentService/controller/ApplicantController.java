package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.entity.Recruter;
import com.example.RecruitmentService.entity.User;
import com.example.RecruitmentService.service.impl.ApplicantServiceImpl;
import com.example.RecruitmentService.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/applicants")
public class ApplicantController {
    private final ApplicantServiceImpl applicantServiceImpl;
    private final UserServiceImpl userServiceImpl;

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
    @GetMapping("/{id}/update/user")
    public String updateApplicantUser(@PathVariable(value = "id") Integer id, Model model, Principal principal) {
        User user=userServiceImpl.getUserByUserName(principal);
        Integer id_applicant=user.getApplicant().getId_applicant();
        Applicant applicant= applicantServiceImpl.findById(id_applicant);
        model.addAttribute("applicant",applicant);
        return "applicantUpdateUser";
    }
    @GetMapping("/choose")
    public String chooseRating(Model model){
        List<Applicant> applicants = applicantServiceImpl.listApplicant();
        for(Applicant applicant: applicants){
            System.out.println(applicant.getId_applicant());
        }
        model.addAttribute( "applicants", applicants);
        return "chooseAppl";
    }

    @GetMapping("/calculation/{id}")
    public String calculationRating(Model model, @PathVariable("id") Integer id){
        Applicant applicant = applicantServiceImpl.findById(id);
        model.addAttribute(applicant);
        return "calculation";
    }

    @GetMapping("/user")
    public String infoApplicant(Principal principal, Model model){
        User user=userServiceImpl.getUserByUserName(principal);
        Integer id=user.getApplicant().getId_applicant();
        Applicant applicant= applicantServiceImpl.findById(id);
        model.addAttribute("applicant", applicant);
        return "applicant-detailsUser";
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

    @PostMapping("/{id}/update/user")
    public String postUpdateApplicantUser(Principal principal, Model model, @PathVariable Integer id,@RequestParam String specialization, @RequestParam String education, @RequestParam String experience, @RequestParam String language){
        applicantServiceImpl.updateUser(id,specialization, education, experience, language);
        User user=userServiceImpl.getUserByUserName(principal);
        Integer id_applicant=user.getApplicant().getId_applicant();
        Applicant applicant= applicantServiceImpl.findById(id_applicant);
        model.addAttribute("applicant",applicant);
        return "applicant-detailsUser";
    }


    @PostMapping("/{id}/remove")
    public String removeApplicant(@PathVariable(value = "id") Integer id, Model model) {
        applicantServiceImpl.removeApplicant(id);
        return "redirect:/applicants";
    }
    @PostMapping("/{id}/remove/user")
    public String removeApplicantUser(Principal principal, @PathVariable(value = "id") Integer id, Model model) {
        User user=userServiceImpl.getUserByUserName(principal);
        Integer id_applicant=user.getApplicant().getId_applicant();
        Applicant applicant= applicantServiceImpl.findById(id_applicant);
        applicantServiceImpl.removeApplicant(id_applicant);
        model.addAttribute("applicant",applicant);
        return "applicant-detailsUser";
    }

    @PostMapping("calculation/{id}/rating")
    public String saveRating(@PathVariable(value = "id") Integer id, Model model, @RequestParam Integer rating ){
        Applicant applicant= applicantServiceImpl.findById(id);
        System.out.println(applicant.getId_applicant());
        model.addAttribute("applicant",applicant);
        return "chooseAppl";
    }
}
