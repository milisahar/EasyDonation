package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor

public class UserService  implements IUserServices {

    private final IUserRepository userRepository;
    private final MailerService mailerService;
    private final RandomString randomString;

    private final PasswordEncoder encoder;


    @Override
    public List<User> retrieveAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

   /* @Override
    public User addUser(User u){
       String code = randomString.randomGeneratedString(8);
        LocalDateTime d = LocalDateTime.now();
       mailerService.sendEmail(u.getEmail(),"Account Creation"," Hello "+u.getFirstName()+" "+u.getLastName()+"\n Your account is almost created, please Login and write this code in the right field to complete your inscription !\n Your code is :"+ code);
    //    u.setStatus(code);
        u.setCreatedAt(d);

        return userRepository.save(u);
    } */

    @Override
    public User updateUser(User u) {
        mailerService.sendEmail(u.getEmail(),"User Modification"," Hello "+u.getFirstName()+" "+u.getLastName()+"\n Your account informations are modified successfully !");
        return userRepository.save(u);
    }

    @Override
    public User retrieveUser(Integer idUser) {
        return userRepository.findById(idUser).orElse(null);
    }
    @Override
    public void removeUser(Integer idUser) {
        userRepository.deleteById(idUser);
    }
    @Override
    public List<User> rechercheDynamique(String search) {

        return userRepository.rechercheDynamique(search);
    }
    @Override
    public String Verification( String email,String code){
        User u = userRepository.findByEmail(email);
        if (u == null){
            return "Wrong or Unexisting Email";
        } else if (u.getStatus()){
            return "User already Verified";
        } else if ((u.getCode()).equals(code)){
            u.setStatus(true);
            u.setCode(null);
            userRepository.save(u);
            return "Your account is now Verified you can proceed to Logging In";
        } else return "Verification Code is Incorrect";


    }
//@Scheduled(cron="*/10 * * * * *")
    public void DeleteNonVerifiedAccounts(){
        LocalDateTime d = LocalDateTime.now().minusHours(24);
        List<User> list = new ArrayList<>();
        userRepository.findByStatusIsNullAndCreatedAtIsBefore(d).forEach(list::add);
        for (User u : list){
           System.out.println(u.getFirstName());
            userRepository.deleteById(u.getId());
        }

    }
@Override
    public String forgotPassword(String email){
        User u = userRepository.findByEmail(email);
        if (u == null ){
            return "User not found ! ";
        }
        else {
            String code = randomString.randomGeneratedString(8);
            u.setCode(code);
            userRepository.save(u);
            mailerService.sendEmail(email, "Account Recovery", "Please use this code :  " + code + "  to recover your account !  ");
            return "Recovery Code sent successfully to your Email ! ";
        }

}

public String resetPassword(String verifCode,String newPass){
        User u = userRepository.findByCode(verifCode);
    if (u == null ){
        return "Wrong Code ! ";
    }
    else {
        u.setPassword(encoder.encode(newPass));
        u.setCode(null);
        userRepository.save(u);
    }
    return "Your password is Changed Successfully ! Please Proceed to Logging In " ;

}






}
