package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;
    @NonNull
    private int phoneNumber;
    String cinUser;
    @JsonIgnore
    Boolean status;
    @JsonIgnore
    String code;
    @JsonIgnore
    LocalDateTime createdAt;
   // @Enumerated(EnumType.STRING)
   // private RoleType role;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();
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
    @OneToMany(mappedBy = "organizer",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Fundraiser> fundraiserList;
    @OneToMany(mappedBy = "userRef")
    private List <FundDonation> fundDonations;
    @OneToMany(mappedBy = "user")
    private List<GiveAwayItem> giveAwayItems;
    @OneToMany (mappedBy = "owner")
    private List<ArticleComment> articleComments;
    @OneToMany (mappedBy = "owner")
    private List<EventComment> eventComments;
    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> messages;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reclamation> reclamations = new ArrayList<>();
}
