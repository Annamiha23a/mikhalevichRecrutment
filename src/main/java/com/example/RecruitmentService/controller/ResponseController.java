package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Recruter;
import com.example.RecruitmentService.entity.Response;
import com.example.RecruitmentService.service.impl.RecruterServiceImpl;
import com.example.RecruitmentService.service.impl.ResponseServiceImpl;
import com.example.RecruitmentService.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.RecruitmentService.entity.User;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/responses")
public class ResponseController {
    private final ResponseServiceImpl responseServiceImpl;
    private final UserServiceImpl userServiceImpl;
    @GetMapping("")
    public String findAllResponse(Model model, @RequestParam(name="status", required = false) String status) {
        List<Response> responses=responseServiceImpl.listResponse(status);
        model.addAttribute("responses", responses);
        return "responses";
    }
    @GetMapping("/{id}")
    public String findResponse(@PathVariable("id") Integer id, Model model) {
        Response response = responseServiceImpl.findById(id);
        if ("Не просмотрено".equals(response.getStatus())) {
            response.setStatus("Просмотрено");
            responseServiceImpl.updateStatus(id, "Просмотрено");
        }
        model.addAttribute("response", response);
        return "response-details";
    }
    @GetMapping("/my")
    public String findMyResponse(Principal principal, Model model, @RequestParam(name="status", required = false) String status){
        User user=userServiceImpl.getUserByUserName(principal);
        Applicant applicant=user.getApplicant();
        List<Response> responses;
        responses=responseServiceImpl.findByApplicantAndStatus(applicant, status);
        model.addAttribute("responses", responses);
        return "responseUser";

    }

    @GetMapping("/my/{id}")
    public String findMyResponse(@PathVariable("id") Integer id, Model model) {
        Response response = responseServiceImpl.findById(id);
        model.addAttribute("response", response);
        return "response-detailsUser";
    }

    @PostMapping("/{id}/update/accept")
    public String acceptPesponse(@PathVariable Integer id, Model model){
        responseServiceImpl.updateStatus(id, "Одобрено");
        Response response= responseServiceImpl.findById(id);
        model.addAttribute("response", response);
        return "response-details";
    }
    @PostMapping("/{id}/update/reject")
    public String rejectPesponse(@PathVariable Integer id, Model model){
        responseServiceImpl.updateStatus(id, "Отказано");
        Response response= responseServiceImpl.findById(id);
        model.addAttribute("response", response);
        return "response-details";
    }

    @PostMapping("/{id}/send")
    public String sendTestByMail(@PathVariable Integer id, @RequestParam String text, Model model, Principal principal){
        User user=responseServiceImpl.getUserByUserName(principal);
        Integer id_user= user.getId();
        if(text==null){
            text="С";
        }
        responseServiceImpl.sendByMail(id, text, id_user);
        Response response= responseServiceImpl.findById(id);
        model.addAttribute("response", response);
        return "response-details";
    }
    @PostMapping("/{id}/remove")
    public String removeResponse(@PathVariable Integer id, Model model){
        responseServiceImpl.removeResponse(id);
        Response response= responseServiceImpl.findById(id);
        model.addAttribute("response", response);
        return "redirect:/responses";
    }

}
