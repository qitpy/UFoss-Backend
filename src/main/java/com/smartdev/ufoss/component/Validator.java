package com.smartdev.ufoss.component;

import java.util.regex.Pattern;

public class Validator {
    public static Boolean emailValidate(String email) {
        String regexEmail = "^[\\w]+@[^@\\s]+\\.[^@\\s]+$";
        if ((Pattern.matches(regexEmail, email))) {
            return true;
        }

        return false;
    }
}
