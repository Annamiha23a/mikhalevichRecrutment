package com.example.RecruitmentService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


import javax.persistence.*;

@Entity
@Table(name="response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_response")
    private Integer id_response;

    private LocalDateTime dateOfCreate;

    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    @Column(name="status")
    private String status;

    @Column(name="comment")
    private String comment;

    @Column(name="gitHub")
    private String gitHub;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REFRESH, fetch =FetchType.LAZY)
    @JoinColumn(name="id_vacancy")
    private Vacancy vacancy;

    @OneToOne(cascade = CascadeType.REFRESH, fetch =FetchType.LAZY)
    @JoinColumn(name="id_applicant")
    private Applicant applicant;

    @Override
    public String toString(){
        return ""+id_response;
    }
}


