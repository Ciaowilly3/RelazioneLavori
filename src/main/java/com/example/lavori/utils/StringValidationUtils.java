package com.example.lavori.utils;


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
        if (StringUtils.isBlank(source) || !source.matches("^[a-zA-Z]$")){
            return false;
        }
        return true;
    }
    // TODO: Scrivere un metodo analogo utilizzando le Regex per validare filters
    // TODO: Provare a validare i filters direttamente dal controller, cercare @Pattern
}
