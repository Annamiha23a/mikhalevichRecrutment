package com.example.RecruitmentService.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="firm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Firm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_firm")
    private int id_firm;

    @Column(name="title")
    private String title;

    @Column(name="field")
    private String field;

    @Column(name="year")
    private Integer year;

    @Column(name="website")
    private String website;

    @Column(name="director")
    private String director;

    @Column(name="workPhone")
    private String workPhone;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "firm")
    private List<Vacancy> vacancies = new ArrayList<>();

}

