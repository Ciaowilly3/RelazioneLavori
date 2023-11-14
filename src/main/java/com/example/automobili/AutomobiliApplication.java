package com.example.automobili;

import com.example.automobili.classes.Auto;
import com.example.automobili.classes.Camion;
import com.example.automobili.classes.Punto;
import com.example.automobili.interfaces.Veicolo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;

@Slf4j
public class AutomobiliApplication {
    public static void main(String[] args){
        val auto = new Auto();
        val camion = new Camion();
        val punto = new Punto();
        List<Veicolo> autoList = List.of(new Auto(), new Camion(), new Punto());
        for (val veicolo : autoList) {
            if (veicolo instanceof Punto puntoTurbo){
                puntoTurbo.getTurbo();
            }
            veicolo.getTipoMezzo();
        }
        log.info("Ecco il tipo del primo mezzo: {}", auto.getTipoMezzo());
        log.info("Ecco il tipo del secondo mezzo: {}", camion.getTipoMezzo());
        log.info("Ecco il tipo del terrzo mezzo: {}", punto.getTipoMezzo());
    }
}
