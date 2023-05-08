package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Fundraiser;
import com.example.easydonatemaster.entites.User;

import java.util.Date;
import java.util.List;

public interface IFundraiser {
    public Fundraiser addFundraiser(Fundraiser fundraiser);
    public List<Fundraiser> listFundraisers();

    public Fundraiser findFundraiserById( int id);
    public List<Fundraiser> findFundraisersByDate(Date deadline);
    public Fundraiser modifyFundraiser(Fundraiser fundraiser);
    public Fundraiser modifyFundraiserById(Fundraiser fundraiser, int id);

    public void deleteFundraiser( int id);
    public List<Fundraiser> listFundraisersByArchived(boolean archived);
    public List<Fundraiser> listFundraisersExipredNotSucceded();
    public Fundraiser bestFundraiser();
    public void checkAvailability();

}
