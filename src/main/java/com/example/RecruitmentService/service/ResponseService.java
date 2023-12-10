package com.example.RecruitmentService.service;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Response;
import com.example.RecruitmentService.entity.Vacancy;

import java.io.IOException;
import java.util.List;

public interface ResponseService {
    Response findById(int id);
    List<Response> listResponse(String status);
    List<Response> listResponse();
    void saveResponse(Response response, String status, String comment, String gitHub, Vacancy vacancy, Applicant applicant) throws IOException;
    void update( Integer id_response, String status, String comment, String gitHub, Vacancy vacancy, Applicant applicant);
    void updateStatus( Integer id_response, String status);
    void removeResponse(int id_response);

    void sendByMail(Integer id, String test, Integer id_user);
    List<Response> findByApplicant(Applicant applicant);
}
