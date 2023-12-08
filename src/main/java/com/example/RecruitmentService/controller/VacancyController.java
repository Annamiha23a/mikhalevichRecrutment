package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Recruter;
import com.example.RecruitmentService.entity.User;
import com.example.RecruitmentService.entity.Vacancy;
import com.example.RecruitmentService.service.VacancyService;
import com.example.RecruitmentService.service.impl.FirmServiceImpl;
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

    @GetMapping("/vacancy")
    public String findAllVacancy(Model model, @RequestParam(name="position", required = false) String position) {
        List<Vacancy> vacancies=vacancyServiceImpl.listVacancy(position);
        model.addAttribute("vacancies", vacancies);
        return "vacancy";
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

    @GetMapping("vacancy/{id_vacancy}/update")
    public String updateVacancy(@PathVariable(value = "id_vacancy") Integer id_vacancy, Model model) {
        Vacancy vacancy = vacancyServiceImpl.findById(id_vacancy);
        model.addAttribute("vacancy",vacancy);
        return "vacancyUpdate";
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
}