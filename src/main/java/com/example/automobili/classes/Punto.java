package com.example.automobili.classes;

public class Punto extends Auto{

    @Override
    public String getTipoMezzo(){

        return "sono una Punto " + super.getTipoMezzo();
    }

    public String getTurbo(){
        return "Sono Turbo";
    }
}
