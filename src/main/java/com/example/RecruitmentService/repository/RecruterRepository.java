package com.example.RecruitmentService.repository;

import com.example.RecruitmentService.entity.Recruter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RecruterRepository extends JpaRepository<Recruter, Integer> {

        Optional<Recruter> findById(int id_recruter);
        List<Recruter> findBySpecializationContaining(String spezialization);
}
