package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.Fundraiser;
import com.example.easydonatemaster.services.IFundraiser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
public class FundraiserController {
    IFundraiser iFundraiserService;

    public FundraiserController(IFundraiser iFundraiserService) {
        this.iFundraiserService = iFundraiserService;
    }

    @PostMapping("/addFundraiser")
    public Fundraiser addFundraiser(@RequestBody Fundraiser fundraiser){
        return iFundraiserService.addFundraiser(fundraiser);
    }
    @GetMapping("/listFundraisers")
    public List<Fundraiser> listFundraisers(){
        return iFundraiserService.listFundraisers();
    }
    @PutMapping("/modifyFundraiser")
    public Fundraiser modifyFundraiser(@RequestBody Fundraiser fundraiser){
        return iFundraiserService.modifyFundraiser(fundraiser);
    }
    @PutMapping("/modifyFundraiser/{id}")
    public Fundraiser modifyFundraiserById(@RequestBody Fundraiser fundraiser,@PathVariable int id){
        return iFundraiserService.modifyFundraiserById(fundraiser,id);
    }
    @GetMapping ("/findFundraiserById/{id}")
    public Fundraiser findFundraiserById(@PathVariable int id){
        return iFundraiserService.findFundraiserById(id);
    }
    @DeleteMapping("/deleteFundraiser/{id}")
    public void deleteFundraiser(@PathVariable int id){
        iFundraiserService.deleteFundraiser(id);
    }
    @GetMapping("/listFundraisersByDate")
    public List<Fundraiser> listFundraisersByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline){
        return iFundraiserService.findFundraisersByDate(deadline);
    }

    @GetMapping("/listFundraisersByArchived/{archived}")
    public List<Fundraiser> listFundraisersByArchived(@PathVariable boolean archived){
        return iFundraiserService.listFundraisersByArchived(archived);
    }
    @GetMapping("/bestFundraiser")
    public Fundraiser bestFundraiser(){
        return iFundraiserService.bestFundraiser();
    }
    @GetMapping("/listFundraisersExipredNotSucceded")
    public List<Fundraiser> listFundraisersExipredNotSucceded(){
        return iFundraiserService.listFundraisersExipredNotSucceded();
    }

}
