package com.example.automobili.classes;

import com.example.automobili.interfaces.Veicolo;

public class Auto implements Veicolo {

    @Override
    public String getTipoMezzo(){
        return "sono un automobile";
    }
}
