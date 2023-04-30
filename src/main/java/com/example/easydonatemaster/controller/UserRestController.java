package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.IUserRepository;
import com.example.easydonatemaster.services.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")


public class UserRestController {

    private final IUserServices userServices;
    private final IUserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    List<User> getAllUsers(){

        return userServices.retrieveAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    User getUser(@PathVariable("id") Integer idUser){

        return userServices.retrieveUser(idUser);
    }

    @GetMapping("/getMyAccount")
    User getMyAccount(Principal principal){
        if (principal.getName()!=null) {
            String username = principal.getName();
            User user = userRepository.findUserByUsername(username);
            Integer idCurrent = user.getId();


            return userServices.retrieveUser(idCurrent);
        }
        else {
            System.out.println("Please SignIn or SignUp");
            return null;
        }
    }



    @PutMapping("/update")
    User updateUser(@RequestBody User u){
        return userServices.updateUser(u);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void deleteUser(@PathVariable("id") Integer idUser){
        userServices.removeUser(idUser);
    }
    @GetMapping("/rechercheDynamique")
    List<User> searchUsers(@RequestParam(required = false) String recherche) {
        return userServices.rechercheDynamique(recherche);
    }



}

