package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Candidacy;
import com.example.easydonatemaster.entites.CandidacyNotFoundException;
import com.example.easydonatemaster.entites.StatusCandidacy;
import com.example.easydonatemaster.repositories.CandidacyRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class CandidacyService implements ICandidacyService{

    CandidacyRepositoy candidacyRepositoy;

    MailerService javaMailSender;
    @Override
    public Candidacy addCandidacy(String volunteerName,Date birthDate,String skills,String email,Date startDate,Date endDate,MultipartFile photoData) throws IOException{
        Candidacy candidacy = new Candidacy();
        candidacy.setVolunteerName(volunteerName);
        candidacy.setBirthDate(birthDate);
        candidacy.setSkills(skills);
        candidacy.setEmail(email);
        candidacy.setStartDate(startDate);
        candidacy.setEndDate(endDate);
        candidacy.setStatus(StatusCandidacy.IN_PROGRESS);
        if (photoData != null && !photoData.isEmpty()){
            candidacy.setPhotoData(photoData.getBytes());
            candidacy.setPhotoType(photoData.getContentType());
        }
        return candidacyRepositoy.save(candidacy);
    }

    @Override
    public Candidacy updateCandidacy(Candidacy candidacy) {
        return candidacyRepositoy.save(candidacy);
    }

    @Override
    public List<Candidacy> getCandidacies() {
        return candidacyRepositoy.findAll();
    }

    @Override
    public Candidacy getCandidacy(int id) {

        return candidacyRepositoy.findById(id)
                .orElseThrow(() -> new CandidacyNotFoundException(id));
    }

    @Override
    public void deleteCandidacy(int id) {
        candidacyRepositoy.deleteById(id);
    }
    /*
    @Override
    public void updateCandidacyStatus(StatusCandidacy status, int id) {
        Candidacy candidacy=candidacyRepositoy.findById(id)
                .orElseThrow(()-> new CandidacyNotFoundException(id));
        candidacy.setStatus(status);
        candidacyRepositoy.save(candidacy);
        String toEmail=candidacy.get

    }
    */
    private String generateCode() {
        final int length = 6;
        final String characters = "0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

    @Override
    public Candidacy acceptCandidacy(int id,String dateEntretien) {
        Candidacy candidacy = candidacyRepositoy.findById(id)
                .orElseThrow(() -> new CandidacyNotFoundException(id));
        candidacy.setStatus(StatusCandidacy.ACCEPTED);
        candidacy.setDateEntretien(dateEntretien);
        candidacy.setCode(generateCode());
        candidacyRepositoy.save(candidacy);

        String to = candidacy.getEmail();
        String subject = "Candidature acceptée";
        String body= "Félicitations, Votre candidature a été acceptée. Vous avez un " +
                "entretien en vidéo conférence le "
                +candidacy.getDateEntretien()
                //+ new SimpleDateFormat("dd/MM/yyyy").format(dateEntretien)
                + " avec le code d'accès suivant : " + candidacy.getCode()
                + "id :" +candidacy.getId() + ".";
        //try {
            javaMailSender.sendEmail(to, subject, body);
        //}
        /*catch (MessagingException e) {
            e.printStackTrace();
        }*/
        return candidacy;
    }

    @Override
    public Candidacy rejectCandidacy(int id) {
        Candidacy candidacy = candidacyRepositoy.findById(id)
                .orElseThrow(() -> new CandidacyNotFoundException(id));
        candidacy.setStatus(StatusCandidacy.REJECTED);
        candidacyRepositoy.save(candidacy);

        String to = candidacy.getEmail();
        String subject = "Candidature rejetée";
        String body= "Nous regrettons de vous informer que votre candidature a été rejetée. ";

        //try {
        javaMailSender.sendEmail(to, subject, body);
            /*
        } catch (MessagingException e) {
            e.printStackTrace();
        }

         */
        return  candidacy;
    }

    @Override
    public boolean verifyCode(int code, int id) {
        Candidacy candidacy = candidacyRepositoy.findById(id).get();
        int x= Integer.parseInt(candidacy.getCode());
        if (x==code) {
            return true;
        }else
        {return false;}
    }


}
