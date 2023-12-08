package com.example.RecruitmentService.repository;

import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Integer> {
    Optional<Firm> findById(int id_firm);
    List<Firm> findByTitleContaining(String title);

    @Query("SELECT f FROM Firm f\n" +
            "INNER JOIN Recruter rec ON f.id_firm = rec.firm.id_firm\n" +
            "INNER JOIN User us ON rec.user.id=us.id\n" +
            "WHERE us.username = ?1")
    Optional<Firm> findByUserName(String username);
}
