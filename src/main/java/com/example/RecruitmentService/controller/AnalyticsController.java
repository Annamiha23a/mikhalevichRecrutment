package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.excel.Write;
import com.example.RecruitmentService.service.AnalyticsService;
import com.example.RecruitmentService.service.impl.AnalyticsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnalyticsController {
    public final AnalyticsServiceImpl analyticsServiceImpl;

    @GetMapping("/analytics")
    public String analytics(Model model){
        model.addAttribute("userSize", analyticsServiceImpl.userSize());
        model.addAttribute("appSize", analyticsServiceImpl.applicantSize());
        model.addAttribute("recrSize", analyticsServiceImpl.recruterSize());
        model.addAttribute("firmSize", analyticsServiceImpl.firmSize());
        model.addAttribute("vacancySize", analyticsServiceImpl.vacancySize());
        model.addAttribute("responseSize", analyticsServiceImpl.responseSize());
        return "analytics";
    }

    @GetMapping("/analyticSave")
    public String saveExcel( Model model){
        Write.writeFirm(analyticsServiceImpl.firmAll());
        Write.writeVacancy(analyticsServiceImpl.vacancyAll());
        Write.writeResponse(analyticsServiceImpl.responseAll());
        model.addAttribute("userSize", analyticsServiceImpl.userSize());
        model.addAttribute("appSize", analyticsServiceImpl.applicantSize());
        model.addAttribute("recrSize", analyticsServiceImpl.recruterSize());
        model.addAttribute("firmSize", analyticsServiceImpl.firmSize());
        model.addAttribute("vacancySize", analyticsServiceImpl.vacancySize());
        model.addAttribute("responseSize", analyticsServiceImpl.responseSize());
        model.addAttribute("status", "Успешно сохранено");
        return "analytics";
    }
}
