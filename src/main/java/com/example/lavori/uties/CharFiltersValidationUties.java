package com.example.lavori.uties;

public class CharFiltersValidationUties {
    public static boolean charFiltersValidation(String filters){
        if (filters.isEmpty() || filters == null){
            return false;
        }
        for (char c: filters.toCharArray()){
            if (!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
}
