package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Urgency urgency;
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @Temporal(TemporalType.DATE)
    private Date endingingDate;
    @Enumerated(value = EnumType.STRING)
    private Progress progress = Progress.STILL;
    @ManyToOne
    @JsonBackReference
    private User assignedTo ;
    @OneToMany (mappedBy = "task" , cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<CheckPoint> checkPoints = new ArrayList<>();
    @OneToMany (mappedBy = "task" , cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<HelpDemand> helpDemands = new ArrayList<>();
}

