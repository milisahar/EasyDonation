package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.HelpDemand;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.entites.User;

import java.util.List;

public interface IHelpDemandService {
    HelpDemand addHelpDemand (HelpDemand helpDemand);
    HelpDemand updateHelpDemand (HelpDemand helpDemand);
    List<HelpDemand> getHelpDemands();
    List<HelpDemand> getHelpDemandsResponded();

    HelpDemand getHelpDemand (int id);
    void deleteHelpDemand (int id);
    int respondedToState(boolean state);
    Task upgradeHelpDemand (HelpDemand helpDemand);
    User findBySender (HelpDemand  task);

    List<HelpDemand> ordered ();
    List<HelpDemand> orderedDst ();
    Task respondToRequest (int id , HelpDemand helpDemand);
}