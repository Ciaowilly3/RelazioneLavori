package com.example.lavori.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
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

}
