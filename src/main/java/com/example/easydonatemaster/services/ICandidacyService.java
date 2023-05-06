package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Candidacy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ICandidacyService {
    public Candidacy addCandidacy(String volunteerName,Date birthDate,String skills,String email,Date startDate,Date endDate,MultipartFile photoData) throws IOException;
    Candidacy updateCandidacy(Candidacy candidacy);
    List<Candidacy> getCandidacies();
    Candidacy getCandidacy(int id);
    void deleteCandidacy(int id);
    //void updateCandidacyStatus(StatusCandidacy status, int id);
    public Candidacy acceptCandidacy(int id,String dateEntretien);
    public Candidacy rejectCandidacy(int id);
    //public void sendEmail(String to, String subject, String text);
    public boolean verifyCode(int code, int id);

}
