package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Donation;
import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.User;

import java.util.Date;
import java.util.List;

public interface IDonationService {

    public Donation addDonation(Donation donation);//
    public List<Donation> listDonations();//
    public Donation findDonationById( int id);//
    public List<Donation> listDonationsByDate(Date date);//
    public List<Donation> listDonationsByUser(User user);
    List<User> TopThreeUsers();
}
