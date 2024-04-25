package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.*;
import com.example.RecruitmentService.service.VacancyService;
import com.example.RecruitmentService.service.impl.FirmServiceImpl;
import com.example.RecruitmentService.service.impl.ResponseServiceImpl;
import com.example.RecruitmentService.service.impl.UserServiceImpl;
import com.example.RecruitmentService.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
//@RequestMapping(value = "")
public class VacancyController {

    private final VacancyServiceImpl vacancyServiceImpl;
    private final FirmServiceImpl firmServiceImpl;
    private final UserServiceImpl userServiceImpl;

    private final ResponseServiceImpl responseServiceImpl;

    @GetMapping("/vacancy")
    public String findAllVacancy(Model model, @RequestParam(name="position", required = false) String position, @RequestParam(name="salery1", required = false) Integer salery1, @RequestParam(name="salery2", required = false) Integer salery2) {
        List<Vacancy> vacancies=vacancyServiceImpl.listVacancy(position, salery1, salery2);
        model.addAttribute("vacancies", vacancies);
        return "vacancy";
    }

    @GetMapping("/vacancy/user")
    public String findAllVacancyUser(Model model, @RequestParam(name="position", required = false) String position) {
        List<Vacancy> vacancies=vacancyServiceImpl.listVacancy(position);
        model.addAttribute("vacancies", vacancies);
        return "vacancyUser";
    }

    @GetMapping("/vacancy/add")
    public String addVacan(Model model){
        List<Firm> firms = firmServiceImpl.listFirm();
        model.addAttribute("firms", firms);
        return "addVacancy";
    }

    @GetMapping("/vacancy/{id_vacancy}")
    public String findVacancy(@PathVariable("id_vacancy") Integer id_vacancy, Model model) {
        Vacancy vacancy= vacancyServiceImpl.findById(id_vacancy);
        model.addAttribute("vacancy", vacancy);
        return "vacancy-details";
    }

    @GetMapping("/vacancy/{id_vacancy}/user")
    public String findVacancyUser(@PathVariable("id_vacancy") Integer id_vacancy, Model model) {
        Vacancy vacancy= vacancyServiceImpl.findById(id_vacancy);
        model.addAttribute("vacancy", vacancy);
        return "vacancy-detailsUser";
    }

    @GetMapping("/vacancy/{id_vacancy}/response/add")
    public String addResponseUser(@PathVariable("id_vacancy") Integer id_vacancy, Model model, Principal principal) {
        User user=userServiceImpl.getUserByUserName(principal);
        Vacancy vacancy= vacancyServiceImpl.findById(id_vacancy);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("user", user);
        return "responseAdd";
    }

    @GetMapping("vacancy/{id_vacancy}/update")
    public String updateVacancy(@PathVariable(value = "id_vacancy") Integer id_vacancy, Model model) {
        Vacancy vacancy = vacancyServiceImpl.findById(id_vacancy);
        model.addAttribute("vacancy",vacancy);
        return "vacancyUpdate";
    }

    @GetMapping("/vacancy/{id_vacancy}/firm")
    public String infoFirmUser(@PathVariable("id_vacancy") Integer id_vacancy, Model model) {
        Vacancy vacancy= vacancyServiceImpl.findById(id_vacancy);
        Firm firm=vacancy.getFirm();
        model.addAttribute("firm", firm);
        return "firm-detailsUser";
    }
    @PostMapping("/vacancy/add")
    public String addVacancy(Principal principal, Vacancy vacancy, @RequestParam("position") String position, @RequestParam("requirements") String requirements, @RequestParam("responsibilities") String responsibilities, @RequestParam("salary") Integer salary , @RequestParam String conditions, @RequestParam String keySkills, Model model) throws IOException {
        Firm firm=firmServiceImpl.getFirmByUserName(principal);
        Integer id_firm=firm.getId_firm();
        System.out.println("id фирмы"+id_firm);
        vacancyServiceImpl.saveVacancy(vacancy, position, requirements, responsibilities, salary, conditions, keySkills,  firm);
        List<Vacancy> vacancies=vacancyServiceImpl.listVacancy();
        model.addAttribute("vacancies", vacancies);
        return "redirect:/vacancy";
    }

    @PostMapping("/vacancy/{id_vacancy}/update")
    public String postUpdateVacancy( @PathVariable Integer id_vacancy,@RequestParam String position, @RequestParam String requirements, @RequestParam String responsibilities, @RequestParam Integer salary, @RequestParam String conditions, @RequestParam String keySkills){
        vacancyServiceImpl.update(id_vacancy,position,requirements, responsibilities, salary, conditions, keySkills);
        return "redirect:/vacancy";
    }


    @PostMapping("/vacancy/{id_vacancy}/remove")
    public String removeVacancy(@PathVariable(value = "id_vacancy") Integer id_vacancy, Model model) {
        vacancyServiceImpl.removeVacancy(id_vacancy);
        return "redirect:/vacancy";
    }

    @PostMapping("/vacancy/{id_vacancy}/response/add")
    public String addResponse(Principal principal, Response response, @PathVariable Integer id_vacancy, @RequestParam String comment, @RequestParam String gitHub, Model model) throws IOException {
        User user=userServiceImpl.getUserByUserName(principal);
        Applicant applicant=user.getApplicant();
        Vacancy vacancy= vacancyServiceImpl.findById(id_vacancy);
        response.setStatus("Не просмотрено");
        responseServiceImpl.saveResponse(response, "Не просмотрено", comment, gitHub, vacancy, applicant);
        List<Vacancy> vacancies=vacancyServiceImpl.listVacancy();
        model.addAttribute("vacancies", vacancies);
        return "vacancyUser";
    }
}