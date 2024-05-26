package com.example.RecruitmentService.repository;

import com.example.RecruitmentService.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    Optional<Vacancy> findById(int id_vacancy);
    List<Vacancy> findByPositionContaining(String position);

    List<Vacancy> findByPositionContainingAndCityAndSalaryBetween(String position, String city, Integer S1, Integer S2);
    List<Vacancy> findByCityAndSalaryBetween(String city, Integer S1, Integer S2);
    List<Vacancy> findByCity(String city);
    List<Vacancy> findBySalaryBetween(Integer S1, Integer S2);

    List<Vacancy> findBySalaryBefore(Integer S1);
    List<Vacancy> findBySalaryAfter(Integer S2);

}
