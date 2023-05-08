package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.FundDonation;
import com.example.easydonatemaster.entites.Fundraiser;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.services.IFundDonation;
import com.example.easydonatemaster.services.IFundraiser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
public class FundDonationController {
    IFundDonation iFundDonation;

    public FundDonationController(IFundDonation iFundDonation) {
        this.iFundDonation = iFundDonation;
    }

    @PostMapping("/addFundDonation/{id}")
    public FundDonation addFundDonation(@RequestBody FundDonation fundDonation, @PathVariable int id){
        return iFundDonation.addFundDonation(fundDonation,id);
    }
    @GetMapping("/listFundDonations")
    public List<FundDonation> listFundDonations(){
        return iFundDonation.listFundDonations();
    }
    @GetMapping("/TopThreeUsers")
    public List<User> TopThreeUsers(){
        return iFundDonation.TopThreeUsers();
    }
}
