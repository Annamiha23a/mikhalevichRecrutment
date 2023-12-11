package com.example.RecruitmentService.service.impl;

import com.example.RecruitmentService.entity.*;
import com.example.RecruitmentService.mail.EmailDetails;
import com.example.RecruitmentService.mail.EmailService;
import com.example.RecruitmentService.repository.ApplicantRepository;
import com.example.RecruitmentService.repository.ResponseRepository;
import com.example.RecruitmentService.repository.UserRepository;
import com.example.RecruitmentService.repository.VacancyRepository;
import com.example.RecruitmentService.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResponseServiceImpl implements ResponseService {
    private ResponseRepository responseRepository;
    private VacancyRepository vacancyRepository;
    private ApplicantRepository applicantRepository;
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository, VacancyRepository vacancyRepository, ApplicantRepository applicantRepository, UserRepository userRepository){
        this.responseRepository=responseRepository;
        this.vacancyRepository=vacancyRepository;
        this.applicantRepository=applicantRepository;
        this.userRepository=userRepository;
    }
    @Override
    public Response findById(int id) {
        return responseRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException());
    }

    @Override
    public List<Response> listResponse(String status) {
        if(status!= null) return responseRepository.findByStatus(status);
        return responseRepository.findAll();
    }

    @Override
    public List<Response> listResponse() {
        return responseRepository.findAll();
    }

    @Override
    public void saveResponse(Response response, String status, String comment, String gitHub, Vacancy vacancy, Applicant applicant) throws IOException {
        Response responseFromDb = responseRepository.save(response);
        responseFromDb.setVacancy(vacancy);
        responseFromDb.setApplicant(applicant);
        responseRepository.save(responseFromDb);
    }

    @Override
    public void update(Integer id_response, String status, String comment, String gitHub, Vacancy vacancy, Applicant applicant) {
        Response response=responseRepository.findById(id_response).orElse(null);
        if(response!=null){
            response.setStatus(status);
            response.setComment(comment);
            response.setGitHub(gitHub);
            response.setVacancy(vacancy);
            response.setApplicant(applicant);
        }
        responseRepository.save(response);
    }

    @Override
    public void updateStatus(Integer id_response, String status) {
        Response response=responseRepository.findById(id_response).orElse(null);
        if(response!=null){
            response.setStatus(status);
        }
        responseRepository.save(response);
    }

    @Override
    public void removeResponse(int id_response) {
        Response response=findById(id_response);
        responseRepository.delete(response);
    }

    @Override
    public void sendByMail(Integer id, String test, Integer id_user) {
        Response response=findById(id);
        String email=response.getApplicant().getUser().getUsername();
        String firstName=response.getApplicant().getUser().getFirstName();
        String lastName=response.getApplicant().getUser().getLastName();
        String position=response.getVacancy().getPosition();
        String firm=response.getVacancy().getFirm().getTitle();
        User user=userRepository.findUserById(id_user).orElseThrow(()->new NoSuchElementException());
        String firstNameRecruter = user.getFirstName();
        String lastNameRecruter=user.getLastName();
        EmailDetails details = new EmailDetails(email,"Добрый день, "+firstName +" "+ lastName+"! /n Вас приветсвует система рекрутинга. Вы оставляли заявку на вакансию "+ position + " в фирме "+firm+ "Высылаем Вам тест, пожалуйста, пройдите его. Ссылка на тест "  + test +"./n С уважением, рекрутер "+firstNameRecruter + " " + lastNameRecruter+ " !" , "Система рекрутинга", null);
        if (email!=null) {
            emailService.sendSimpleMail(details);
        }
    }


    public User getUserByUserName (Principal principal){
        if(principal==null) return new User();
        String username=principal.getName();
        return userRepository.findUserByUsername(username).orElseThrow(()->new NoSuchElementException());
    }

    public List<Response>  findByApplicant(Applicant applicant){
        if(applicant!= null) return responseRepository.findByApplicant(applicant);
        return null;
    }

    public List<Response>  findByApplicantAndStatus(Applicant applicant, String status){
        if(status!= null) {
            return responseRepository.findByApplicantAndStatus(applicant, status);
              }
        return responseRepository.findByApplicant(applicant);
    }
}
