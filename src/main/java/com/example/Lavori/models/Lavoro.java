package com.example.Lavori.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "l1_0")
public class Lavoro {

    @Id
    @Column(name = "lavoro_id")
    private String lavoroId;
    @Column(name = "lavoro_name")
    private String lavoroName;

    public Lavoro() {
        this.lavoroId = UUID.randomUUID().toString();
    }

    public Lavoro(String lavoroId, String lavoroName) {
        this.lavoroId = lavoroId;
        this.lavoroName = lavoroName;
    }

    public String getLavoroName() {
        return lavoroName;
    }

    public void setLavoroName(String lavoroName) {
        this.lavoroName = lavoroName;
    }

    public String getLavoroId() {
        return lavoroId;
    }
}
