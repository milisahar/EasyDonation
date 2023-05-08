package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.Candidacy;
import com.example.easydonatemaster.services.ICandidacyService;
import com.example.easydonatemaster.services.MailerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CandidacyController {
    ICandidacyService ICandidacyService;
    MailerService javaMailSender;

    @PostMapping("/candidacy")
    Candidacy addCandida(@RequestParam(required = false, name = "volunteerName") String volunteerName,@RequestParam(required = false, name = "birthDate") Date birthDate,@RequestParam(required = false, name = "skills") String skills,@RequestParam(required = false, name = "email") String email,@RequestParam(required = false, name = "startDate") Date startDate,@RequestParam(required = false, name = "endDate") Date endDate, @RequestParam(required = false, name = "photoData") MultipartFile photoData) throws IOException {
        return ICandidacyService.addCandidacy(volunteerName,birthDate,skills,email,startDate,endDate,photoData);
    }
    @PutMapping("/candidacy")
    Candidacy updateCandida(@RequestBody Candidacy candidacy){return ICandidacyService.updateCandidacy(candidacy);}
    @GetMapping("/candidacy/{id}")
    Candidacy getcandida(@PathVariable int id){return ICandidacyService.getCandidacy(id);}
    @GetMapping("/candidacy")
    List<Candidacy> getCandidas(){return ICandidacyService.getCandidacies();}
    @DeleteMapping("/candidacy/{id}")
    void deleteCandida(@PathVariable int id){ICandidacyService.deleteCandidacy(id);}
    /*
    @PutMapping("/{id}/status")
    void updateCandidacyStatus(@RequestBody StatusCandidacy status, @PathVariable int id){
        ICandidacyService.updateCandidacyStatus(status,id);
    }
    */
    @PostMapping("/{id}/accept/{dateEntretien}")
    Candidacy acceptCandidacy(@PathVariable("id") int id, @PathVariable("dateEntretien") String dateEntretien){
         return ICandidacyService.acceptCandidacy(id,dateEntretien);
    }
    @PostMapping("/{id}/reject")
    Candidacy rejectCandidacy(@PathVariable("id") int id){
        return ICandidacyService.rejectCandidacy(id);
    }

    @PostMapping("/verify")
    public boolean verifyCode(@RequestParam(required = false, name = "code") int code,@RequestParam (required = false, name = "id") int id){
        return  ICandidacyService.verifyCode(code,id);
    }

}
