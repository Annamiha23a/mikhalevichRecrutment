package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.Recruter;
import com.example.RecruitmentService.service.impl.RecruterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/recruters")
public class RecruterController {

    private final RecruterServiceImpl recruterServiceImpl;
    //private final FirmServiceImpl firmServiceImpl;

    @GetMapping("")
    public String findAllRecruter(Model model, @RequestParam(name="specialization", required = false) String specialization) {
        List<Recruter> recruters=recruterServiceImpl.listRecruter(specialization);
        model.addAttribute("recruters", recruters);
        return "recruters";
    }

    @GetMapping("/add")
    public String addRecrut(Model model){
//        List<User> users = userServiceImpl.listFirm();
//        model.addAttribute("users", users);
        return "recruterAdd";
    }

    @GetMapping("/{id_recruter}")
    public String findRecruter(@PathVariable("id_recruter") Integer id_recruter, Model model) {
        Recruter recruter= recruterServiceImpl.findById(id_recruter);
        model.addAttribute("recruter", recruter);
        return "recruter-details";
    }

    @GetMapping("/{id_recruter}/update")
    public String updateRecruter(@PathVariable(value = "id_recruter") Integer id_recruter, Model model) {
        Recruter recruter = recruterServiceImpl.findById(id_recruter);
        model.addAttribute("recruter",recruter);
        return "recruterUpdate";
    }


    @PostMapping("/add")
    public String addRecruter(Recruter recruter, @RequestParam String specialization, @RequestParam Integer numberOfHires, @RequestParam String portfolio, @RequestParam String searchSpecification, Model model) throws IOException {
        recruterServiceImpl.saveRecruter(recruter, specialization, numberOfHires, portfolio, searchSpecification);
        List<Recruter> recruters=recruterServiceImpl.listRecruter();
        model.addAttribute("recruters", recruters);
        return "redirect:/recruters";
    }

    @PostMapping("/{id_recruter}/update")
    public String postUpdateRecruter(@PathVariable Integer id_recruter,@RequestParam String specialization, @RequestParam Integer numberOfHires, @RequestParam String portfolio, @RequestParam String searchSpecification){
        recruterServiceImpl.update(id_recruter,specialization, numberOfHires, portfolio, searchSpecification);
        return "redirect:/recruters";
    }


    @PostMapping("/{id_vacancy}/remove")
    public String removeRecruter(@PathVariable(value = "id_recruter") Integer id_recruter, Model model) {
        recruterServiceImpl.removeRecruter(id_recruter);
        return "redirect:/vacancys";
    }
}