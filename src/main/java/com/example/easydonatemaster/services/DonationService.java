package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Donation;
import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.DonationRepositoy;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DonationService implements IDonationService{
    DonationRepositoy donationRepositoy;

    public DonationService(DonationRepositoy donationRepositoy) {
        this.donationRepositoy = donationRepositoy;
    }
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Donation addDonation(Donation donation) {
        return donationRepositoy.save(donation);
    }

    @Override
    public List<Donation> listDonations() {
        return donationRepositoy.findAll();
    }

    @Override
    public Donation findDonationById(int id) {
        return donationRepositoy.findById(id).orElse(null);
    }

    @Override
    public List<Donation> listDonationsByDate(Date donationDate) {

        List<Donation> donations = donationRepositoy.findAllByDonationDate(donationDate);
        return donations;
    }
    @Override
    public List<Donation> listDonationsByUser(User user) {
        return donationRepositoy.findByUserRefOrderBySum(user);
    }

    @Override
    public List<User> TopThreeUsers() {
        List<Object[]> topThreeDonators = donationRepositoy.findTopThreeDonators();
        List<User> result = new ArrayList<>();
        for (int i = 0; i < topThreeDonators.size() && i < 3; i++) {
            Object[] row = topThreeDonators.get(i);
            User user = (User) row[0];
            result.add(user);
        }
        return result;
    }
}
