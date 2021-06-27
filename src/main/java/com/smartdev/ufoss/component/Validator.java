package com.smartdev.ufoss.component;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Validator {

    public static Boolean emailValidate(String email) {
        String regexEmail = "^[\\w][\\w\\.?]+@[^@\\s]+\\.[^@\\s]+$";
        if ((Pattern.matches(regexEmail, email))) {
            return true;
        }

        return false;
    }
}
