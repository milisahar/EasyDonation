package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fundraiser implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int fundraiser_id;
    private String goal;
    private String title;
    private String description;
    private boolean archived;

    @Enumerated(EnumType.STRING)
    private  PendingStatus pendingStatus;
        @Temporal(TemporalType.TIMESTAMP)
        private Date deadline= new Date();
    private float progressStatus;
    private float target;
    @ManyToOne(fetch = FetchType.EAGER)
    private User organizer;
    @OneToMany(mappedBy = "fundraiserRef" , cascade =CascadeType.ALL)
    private Set<FundDonation> fundDonations= new HashSet<>();

}
