package com.example.easydonatemaster.services;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class RandomString {

    public String randomGeneratedString(Integer l) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(l)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

       return generatedString;
    }
}
