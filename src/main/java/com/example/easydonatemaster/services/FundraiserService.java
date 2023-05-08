package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Fundraiser;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.FundraiserRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FundraiserService implements IFundraiser {
    FundraiserRepositoy fundraiserRepositoy;

    public FundraiserService(FundraiserRepositoy fundraiserRepositoy) {
        this.fundraiserRepositoy = fundraiserRepositoy;
    }

    @Override
    public Fundraiser addFundraiser(Fundraiser fundraiser) {
        return fundraiserRepositoy.save(fundraiser);
    }

    @Override
    public List<Fundraiser> listFundraisers() {
        return fundraiserRepositoy.findAll();
    }

    @Override
    public Fundraiser findFundraiserById(int id) {
        return fundraiserRepositoy.findById(id).orElse(null);
    }

    @Override
    public List<Fundraiser> findFundraisersByDate(Date deadline) {
        List<Fundraiser> fundraisers=fundraiserRepositoy.findAllByDeadline(deadline);
        return fundraisers;
    }

    @Override
    public Fundraiser modifyFundraiser(Fundraiser fundraiser) {
        return  fundraiserRepositoy.save(fundraiser);
    }

    @Override
    public Fundraiser modifyFundraiserById(Fundraiser fundraiser, int id) {
        Fundraiser f= fundraiserRepositoy.findById(id).orElse(null);
        fundraiserRepositoy.deleteById(id);
        return fundraiserRepositoy.save(fundraiser);
    }

    @Override
    public void deleteFundraiser(int id) {
        fundraiserRepositoy.deleteById(id);
    }

    @Override
    public List<Fundraiser> listFundraisersByArchived(boolean archived) {
        List<Fundraiser> fundraisers=fundraiserRepositoy.findAllByArchived(archived);
        return fundraisers;
    }

    @Override
    public Fundraiser bestFundraiser() {
        return fundraiserRepositoy.BestFundraiser();
    }

    @Override
    @Scheduled(cron = "* */60 * * * *")
    public void checkAvailability() {
        List<Fundraiser> fundraisers = fundraiserRepositoy.findByArchivedFalse();
        Date today = new Date();
        for (Fundraiser f: fundraisers
             ) {
            if((f.getProgressStatus()==f.getTarget())||(today.after(f.getDeadline()))){
                f.setArchived(true);
                fundraiserRepositoy.save(f);
            }

        }
    }

    public List<Fundraiser> listFundraisersExipredNotSucceded(){
        return fundraiserRepositoy.listFundraisersExipredNotSucceded();
    }

}
