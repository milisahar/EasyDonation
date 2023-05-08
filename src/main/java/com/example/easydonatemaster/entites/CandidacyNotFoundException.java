package com.example.easydonatemaster.entites;

public class CandidacyNotFoundException extends RuntimeException {
    public CandidacyNotFoundException(int id) {
        super("Candidacy Not Found with ID: " + id);
    }
}
