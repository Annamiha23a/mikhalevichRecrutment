package com.example.RecruitmentService.repository;

import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Integer> {
    Optional<Firm> findById(int id_firm);
    List<Firm> findByTitleContaining(String title);
}
