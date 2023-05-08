package com.example.easydonatemaster.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String Username;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> roles;


    private String password;

    private String cinUser;

    private   int phoneNumber;


    private Set<String> role;
}
