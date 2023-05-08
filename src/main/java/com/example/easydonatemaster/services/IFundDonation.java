package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.FundDonation;
import com.example.easydonatemaster.entites.Fundraiser;
import com.example.easydonatemaster.entites.User;

import java.util.List;

public interface IFundDonation {
    FundDonation addFundDonation(FundDonation fundDonation, int id);
    public List<FundDonation> listFundDonations();
    List<User> TopThreeUsers();
}
