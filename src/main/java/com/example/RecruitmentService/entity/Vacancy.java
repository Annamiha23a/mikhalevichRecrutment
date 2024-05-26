package com.example.RecruitmentService.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name="vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacancy")
    private Integer id_vacancy;

    @Column(name="position")
    private String position;

    @Column(name="salary")
    private Integer salary;

    @Column(name="responsibilities")
    private String responsibilities;

    @Column(name="requirements")
    private String requirements;

    @Column(name="conditions")
    private String conditions;

    @Column(name="keySkills")
    private String keySkills;

    @Column(name="city")
    private String city;

    private LocalDateTime dateOfCreate;
    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    //    @OneToMany(mappedBy = "firms")
//    @JsonIgnore
//    private List<Firm> firms;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch =FetchType.LAZY)
    @JoinColumn(name="id_firm")
    private Firm firm;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vacancy")
    private List<Response> responses = new ArrayList<>();

}

