package com.example.lavori.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Table(name = "l1_0")
public class Lavoro {

    @Id
    @Column(name = "lavoro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lavoroId;
    @Column(name = "lavoro_name")
    private String lavoroName;


}
