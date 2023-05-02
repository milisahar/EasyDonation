package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp eventDate;
    private String place;
    private double capacity;
    private int duration;
    private boolean ongoing;
    private String description;
    @Enumerated(EnumType.STRING)
    private  EventType eventType;
    @JsonIgnore
    @OneToMany(mappedBy = "eventRef")
    private List<Ticket> ticketList;
    //admin
    @ManyToOne
    private User organizer;
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List <EventComment> eventComments;

}
