package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.Vacancy;
import com.example.RecruitmentService.service.VacancyService;
import com.example.RecruitmentService.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
//@RequestMapping(value = "")
public class VacancyController {

    private final VacancyServiceImpl vacancyServiceImpl;

    @GetMapping("/vacancy")
    public String findAllVacancy(Model model, @RequestParam(name="position", required = false) String position) {
        List<Vacancy> vacancies=vacancyServiceImpl.listVacancy(position);
        model.addAttribute("vacancies", vacancies);
        return "vacancy";
    }

    @GetMapping("/vacancy/add")
    public String add(){
        return "addVacancy";
    }

    @PostMapping("/vacancy/add")
    public String addVacancy(Vacancy vacancy, @RequestParam("position") String position, @RequestParam("requirements") String requirements, @RequestParam("responsibilities") String responsibilities, @RequestParam("salary") Integer salary, Model model) throws IOException {
        vacancyServiceImpl.saveVacancy(vacancy, position, requirements, responsibilities, salary);
        List<Vacancy> vacancies=vacancyServiceImpl.listVacancy();
        model.addAttribute("vacancies", vacancies);
        return "vacancy";
    }
}