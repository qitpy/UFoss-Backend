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

    public static boolean checkNotNullAndSpace(String... strings) {
        String[] strs = strings;

        for (int i = 0; i < strs.length; i++) {
            if ((strs[i] == null) || "".equals(strs[i])) {
                return false;
            }
        }
        return true;
    }

}
