package com.example.easydonatemaster.Request;

import lombok.Data;

@Data
public class LoginRequest {

    private String Username;

    private String password;


    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }

}
