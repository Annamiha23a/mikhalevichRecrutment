package com.example.RecruitmentService.repository;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Recruter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Optional<Applicant> findById(int id_applicant);
    List<Applicant> findBySpecializationContaining(String spezialization);
}
