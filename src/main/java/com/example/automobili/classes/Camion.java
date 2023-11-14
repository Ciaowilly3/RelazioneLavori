package com.example.automobili.classes;

import com.example.automobili.interfaces.Veicolo;

public class Camion implements Veicolo {

    @Override
    public String getTipoMezzo(){
        return "Sono un camion";
    }
}
