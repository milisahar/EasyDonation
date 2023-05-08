package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelpDemand implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private boolean respondedTo = false; //a la création d'une demande d'aide l'etat de réponse est par defaut non
    @Temporal(TemporalType.DATE)
    private Date dateDeCreation ;
    @Enumerated(value = EnumType.STRING)
    private Urgency urgency;
    @ManyToOne
    @JsonBackReference
    private Task task;
}
