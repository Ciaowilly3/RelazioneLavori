package com.example.lavori.uties;

import com.example.lavori.models.User;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class LogginUties {
    public void logBanana(User user){

        log.error("Banana");
        logBananaPlus();
    }
    private void logBananaPlus(){
        System.out.println("Plus");
    }
}
