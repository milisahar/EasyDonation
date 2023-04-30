package com.example.easydonatemaster.Request.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    String jwt;
    Integer id;
    String username;
    String email;
    List<String> roles;
}
