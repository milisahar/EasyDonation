package com.example.easydonatemaster.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidacy implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private  String volenteerName;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String email;
    private String skills;
    private String experience;
    private String motivation;
    private String adress;

    // admin
    @OneToOne (mappedBy = "candidacy")
    private User user;
}
