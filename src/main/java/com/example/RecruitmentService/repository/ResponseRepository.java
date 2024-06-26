package com.example.RecruitmentService.repository;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface ResponseRepository extends JpaRepository<Response, Integer> {
        Optional<Response> findById(int id_response);
        List<Response> findByStatus(String status);

        List<Response> findByApplicant(Applicant applicant);
        List<Response> findByApplicantAndStatus(Applicant applicant, String status);


    }
