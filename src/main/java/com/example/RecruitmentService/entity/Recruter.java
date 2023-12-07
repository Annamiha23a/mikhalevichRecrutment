package com.example.RecruitmentService.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Table(name="recruter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recruter")
    private Integer id_recruter;

    @Column(name="specialization")
    private String specialization;

    @Column(name="numberOfHires")
    private Integer numberOfHires;

    @Column(name="portfolio")
    private String portfolio;

    @Column(name="searchSpecification")
    private String searchSpecification;

    @OneToOne(cascade = CascadeType.REFRESH, fetch =FetchType.LAZY)
    @JoinColumn(name="id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch =FetchType.LAZY)
    @JoinColumn(name = "id_firm")
    private Firm firm;

}

