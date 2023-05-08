package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.FundDonation;
import com.example.easydonatemaster.entites.Fundraiser;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.FundDonationRepositoy;
import com.example.easydonatemaster.repositories.FundraiserRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class FundDonationService implements IFundDonation{
    FundDonationRepositoy fundDonationRepositoy;
    FundraiserRepositoy fundraiserRepositoy;
    @Override
    public FundDonation addFundDonation(FundDonation fundDonation, int id) {
        Fundraiser f= fundraiserRepositoy.findById(id).orElse(null);
        if (f.isArchived()) {
            throw new RuntimeException("Cannot insert or update fundraiser! it's archived ");
        }
        float sum= fundDonation.getSum()+f.getProgressStatus();
        f.setProgressStatus(sum);
        if(f.getProgressStatus()>f.getTarget()|| f.getProgressStatus()==f.getTarget()){
           f.setArchived(true);
       }
       fundraiserRepositoy.save(f);
       fundDonation.setFundraiserRef(f);
        return fundDonationRepositoy.save(fundDonation);
    }

    @Override
    public List<FundDonation> listFundDonations() {
        return fundDonationRepositoy.findAll();
    }

    @Override
    public List<User> TopThreeUsers() {
        List<Object[]> topThreeDonators = fundDonationRepositoy.findTopThreeDonators();
        List<User> result = new ArrayList<>();
        for (int i = 0; i < topThreeDonators.size() && i < 3; i++) {
            Object[] row = topThreeDonators.get(i);
            User user = (User) row[0];
            result.add(user);
        }
        return result;
    }
}
