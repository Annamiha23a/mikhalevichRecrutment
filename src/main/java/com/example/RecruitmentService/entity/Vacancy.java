package com.example.RecruitmentService.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


import javax.persistence.*;
@Entity
@Table(name="vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacancy")
    private int id_vacancy;

    @Column(name="position")
    private String position;

    @Column(name="salary")
    private String salary;

    @Column(name="responsibilities")
    private String responsibilities;

    @Column(name="requirements")
    private String requirements;

    private LocalDateTime dateOfCreate;
    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    //    @OneToMany(mappedBy = "firms")
//    @JsonIgnore
//    private List<Firm> firms;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch =FetchType.LAZY)
    @JoinColumn
    private Firm firm;

}

