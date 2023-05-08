package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.HelpDemand;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.HelpDemandRepositoy;
import com.example.easydonatemaster.repositories.TaskRepositoy;
import com.example.easydonatemaster.repositories.UserRepositoy;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HelpDemandService implements IHelpDemandService {

    HelpDemandRepositoy helpDemandRepositoy;
    TaskRepositoy taskRepositoy;
    UserRepositoy userRepositoy;
    @PostConstruct
    public void  setUp() {
        Twilio.init("AC145f6e0c5245a6855cb4e4d42ebe95bd", "b5febbead47c99c19003df8228929d68");
    }
    @Override
    public HelpDemand addHelpDemand(HelpDemand helpDemand) {
        Message message = Message.creator(
                new PhoneNumber("+21652635303"),
                new PhoneNumber("+15677066534"),
                "A help demand have been added ! SOMEONE NEEDS YOUR HELP. \n " +
                        "PLEASE be kind enough to help <3 !").create();
        System.out.println(message.getStatus().toString());
        return helpDemandRepositoy.save(helpDemand);

    }

    @Override
    public HelpDemand updateHelpDemand(HelpDemand helpDemand) {
        return helpDemandRepositoy.save(helpDemand);
    }

    @Override
    public List<HelpDemand> getHelpDemands() {
        return helpDemandRepositoy.findAll();
    }

    @Override
    public List<HelpDemand> getHelpDemandsResponded() {
        return helpDemandRepositoy.findByRespondedTo(false);
    }


    @Override
    public HelpDemand getHelpDemand(int id) {
        return helpDemandRepositoy.findById(id).orElse(null);
    }

    @Override
    public void deleteHelpDemand(int id) {
        helpDemandRepositoy.deleteById(id);
    }

    @Override
    public int respondedToState(boolean state) {
        return (helpDemandRepositoy.findByRespondedTo(state).size()*100)/helpDemandRepositoy.findAll().size();
    }

    @Override
    public Task upgradeHelpDemand(HelpDemand helpDemand) {

        Task task = new Task();
        task.setTitle(helpDemand.getTitle());
        task.setUrgency(helpDemand.getUrgency());
        task.setDescription(helpDemand.getContent());
        // task.setProgress(Progress.STILL);
        task.setStartingDate(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)));
        task.setEndingingDate(Date.from(LocalDate.now().plusDays(5).atStartOfDay().toInstant(ZoneOffset.UTC)));
        task.setAssignedTo(userRepositoy.findById(helpDemand.getTask().getId()).orElse(null));
        task.setAssignedTo(userRepositoy.findById(1).orElse(null));
        taskRepositoy.save(task);
        helpDemandRepositoy.deleteById(helpDemand.getId());
        return null;
    }

    @Override
    public User findBySender(HelpDemand help) {

        return userRepositoy.findByTaskListHelpDemands(help);
    }

    @Override
    public List<HelpDemand> ordered() {
        return helpDemandRepositoy.orderdDst();
    }

    @Override
    public List<HelpDemand> orderedDst() {
        return helpDemandRepositoy.orderd();
    }

    @Override
    public Task respondToRequest (int id , HelpDemand helpDemand) {

        Task task = new Task();
        task.setTitle(helpDemand.getTitle());
        task.setUrgency(helpDemand.getUrgency());
        task.setDescription(helpDemand.getContent());
        // task.setProgress(Progress.STILL);
        task.setStartingDate(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)));
        task.setEndingingDate(Date.from(LocalDate.now().plusDays(5).atStartOfDay().toInstant(ZoneOffset.UTC)));
        task.setAssignedTo(userRepositoy.findById(id).orElse(null));
        taskRepositoy.save(task);
        helpDemandRepositoy.deleteById(helpDemand.getId());
        return task;
    }
}