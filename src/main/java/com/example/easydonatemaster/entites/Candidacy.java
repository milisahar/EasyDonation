package com.example.easydonatemaster.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private  String volunteerName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String skills;
    //private String motivation;
    //private String address;
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private StatusCandidacy status; //in progress, acccepted, rejected
    @Lob
    private byte[] photoData;
    //private String photoName;
    private String photoType;
    private String code; // le code d'accès à la vidéo conférence

    private String dateEntretien; // la date de l'entretien en vidéo conférence

    // admin
    @OneToOne (mappedBy = "candidacy")
    private User user;
}
