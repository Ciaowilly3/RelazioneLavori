package com.example.jobs.utils;


import org.apache.commons.lang3.StringUtils;

public class StringValidationUtils {
    public static boolean areOnlyLetters(String source){
        if (StringUtils.isBlank(source)){
            return false;
        }

        //StringUtils.
        for (char c: source.toCharArray()){
            if (!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
    public static boolean areOnlyLettersEgex(String source){
        return !StringUtils.isBlank(source) && source.matches("^[a-zA-Z]$");
    }

}
