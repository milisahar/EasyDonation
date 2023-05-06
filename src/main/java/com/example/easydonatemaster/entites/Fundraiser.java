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
public class Fundraiser implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String goal;
    private String title;
    private String description;
    private boolean archived;
    @Enumerated(EnumType.STRING)
    private  PendingStatus pendingStatus;
    @Temporal(TemporalType.DATE)
    private Date deadline;
    private float progressStatus;
    @ManyToOne
    private User organizer;
    @OneToMany(mappedBy = "fundraiserRef")
    private List<FundDonation> fundDonations;

}
