package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckPoint implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Lob
    private String img;
    @Temporal(TemporalType.DATE)
    private Date dateDeCreation;
    //@JsonBackReference
    @ManyToOne
    @JsonBackReference //@JsonIgnore
    @Null
    private Task task;
    //private string geaoLocalisation;
}
