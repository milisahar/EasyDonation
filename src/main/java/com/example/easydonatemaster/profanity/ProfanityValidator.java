package com.example.easydonatemaster.profanity;

import java.util.Arrays;
import java.util.List;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ProfanityValidator implements ConstraintValidator<ProfanityFilter, String> {

    private static final List<String> PROHIBITED_WORDS = Arrays.asList("farah", "chouikh", "arctic");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (String word : PROHIBITED_WORDS) {
            if (value.toLowerCase().contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

}

