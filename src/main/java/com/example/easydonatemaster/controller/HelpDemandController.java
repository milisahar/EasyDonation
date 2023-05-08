package com.example.easydonatemaster.controller;


import com.example.easydonatemaster.entites.HelpDemand;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.services.IHelpDemandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/help_demands")
@CrossOrigin(origins = "*")
public class HelpDemandController {

    IHelpDemandService iHelpDemandService;

    @PostMapping("/add")
    HelpDemand addHelpDemand (@RequestBody HelpDemand helpDemand) {
        return iHelpDemandService.addHelpDemand(helpDemand);
    }

    @PutMapping("/update/{id}")
    HelpDemand updateHelpDemand (@RequestBody HelpDemand helpDemand) {

        return iHelpDemandService.updateHelpDemand(helpDemand);
    }

    @GetMapping("")
    List<HelpDemand> getHelpDemand(){
        return iHelpDemandService.getHelpDemands();
    }
    @GetMapping("/{id}")
    HelpDemand getHelpDemand(@PathVariable int id){
        return iHelpDemandService.getHelpDemand(id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteHelpDemand(@PathVariable int id){
        iHelpDemandService.deleteHelpDemand(id);
    }

    @GetMapping("/wait")
    List<HelpDemand> respondedNotToList(){
        return iHelpDemandService.getHelpDemandsResponded();
    }

    @GetMapping("/respondedTo")
    String respondedTo(){
        return iHelpDemandService.respondedToState(true) +" %";
    }
    @GetMapping("/notRespondedTo")
    String notRespondedTo(){
        return iHelpDemandService.respondedToState(false) +" %";
    }

    @PostMapping("/upgrading/{id}")
    Task upgrade (@PathVariable int id) {
        return iHelpDemandService.upgradeHelpDemand(iHelpDemandService.getHelpDemand(id));
    }


    @PostMapping("/responding/{id_user}/request/{id}")
    Task respondToRequest (@PathVariable("id_user")int user_id,@PathVariable("id") int id) {
        return iHelpDemandService.respondToRequest(user_id,iHelpDemandService.getHelpDemand(id));
    }

    @GetMapping("/sender/{id}")
    User findBySender (@PathVariable int id) {
        return iHelpDemandService.findBySender(iHelpDemandService.getHelpDemand(id));
    }

    @GetMapping("/orderd")
    List<HelpDemand> ordered () {
        return iHelpDemandService.ordered();
    }

    @GetMapping("/orderd/down")
    List<HelpDemand> orderedDesc () {
        return iHelpDemandService.orderedDst();
    }

    //@GetMapping("/respondedTo")
    //String notRespondedTo(){
    //  return iHelpDemandService.respondedToState(false).size()+" %";
    // }

}