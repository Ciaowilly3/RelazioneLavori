package com.example.lavori.dto;

import com.example.lavori.models.Lavoro;

public class TestGenerics <T>{
    String name;
    T special;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getSpecial() {
        return special;
    }

    public void setSpecial(T special) {
        this.special = special;
       /* var lavoro = Lavoro.builder()
                .lavoroName("nome")
                .lavoroId("id")
                .build();*/
    }
}
