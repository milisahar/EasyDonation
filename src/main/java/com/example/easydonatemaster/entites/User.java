package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String fullName;
    private String email;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;
    private int phoneNumber;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    //admin accepts
    @OneToOne
    private Candidacy candidacy;
    @OneToMany (mappedBy = "userRef", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Donation> donationList;

    @OneToMany(mappedBy = "sender")
    private Set<HelpDemand> helpDemands;
    @OneToMany (mappedBy = "assignedTo")
    private List <Task> taskList;
    @OneToMany (cascade = CascadeType.ALL)
    private List<CheckPoint> checkPoints;

    @OneToMany (mappedBy = "publisher")
    private List<Article> articles;
    @OneToMany(mappedBy = "owner")
    private List<Ticket> tickets;
    //only admin
    @OneToMany(mappedBy = "organizer")
    private List<Event> events;
    @OneToMany(mappedBy = "organizer")
    private List<Fundraiser> fundraiserList;
    @OneToMany(mappedBy = "userRef")
    @JsonManagedReference
    private List <FundDonation> fundDonations;
    @OneToMany(mappedBy = "user")
    private List<GiveAwayItem> giveAwayItems;
    @OneToMany (mappedBy = "owner")
    private List<ArticleComment> articleComments;
    @OneToMany (mappedBy = "owner")
    private List<EventComment> eventComments;
    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> messages;
}
