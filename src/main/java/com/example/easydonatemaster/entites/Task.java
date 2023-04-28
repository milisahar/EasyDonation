package com.example.easydonatemaster.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private Urgency urgency;
    private String flags;
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @Temporal(TemporalType.DATE)
    private Date endingingDate;
    @ManyToOne
    private User assignedTo ;
    @OneToMany (mappedBy = "task", cascade = CascadeType.ALL)
    private List<CheckPoint> checkPoints;
}
