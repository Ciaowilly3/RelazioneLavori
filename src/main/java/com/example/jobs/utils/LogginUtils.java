package com.example.jobs.utils;

import com.example.jobs.models.User;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class LogginUtils {
    public void logBanana(User user){

        log.error("Banana");
        logBananaPlus();
    }
    private void logBananaPlus(){
        System.out.println("Plus");
    }
}
