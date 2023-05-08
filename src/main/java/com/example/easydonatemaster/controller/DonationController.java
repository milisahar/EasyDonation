package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.Donation;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.services.DonationService;
import com.example.easydonatemaster.services.IDonationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins="*")
@RestController
public class DonationController {

    IDonationService iDonationService;

    public DonationController(IDonationService iDonationService) {
        this.iDonationService = iDonationService;
    }
    @PostMapping("/add")
    public Donation addDonation(@RequestBody Donation donation){
        return iDonationService.addDonation(donation);
    }
    @GetMapping ("/listDonation")
    public List<Donation> listDonations(){
        return iDonationService.listDonations();
    }
    @GetMapping ("/findById/{id}")
    public Donation findDonationById(@PathVariable int id){
        return iDonationService.findDonationById(id);
    }
    @GetMapping("/listByDate")
    public List<Donation> listDonationsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date donationDate){
        return iDonationService.listDonationsByDate(donationDate);
    }
    @GetMapping("/listDonationsByUser")
    public  List<Donation> listDonationsByUser(User user){
        return iDonationService.listDonationsByUser(user);
    }
    @GetMapping("/TopThreeDonators")
    public List<User> TopThreeDonators(){
        return iDonationService.TopThreeUsers();

    }
}


