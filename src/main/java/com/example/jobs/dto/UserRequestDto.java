package com.example.jobs.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String userName;
    private String jobName;
    private Long userId;
}
// TODO: RINOMINARE TUTTO IN INGLESE SE ORAZIO TROVA UNA COSA IN ITALIANO MI FORMATTA IL COMPUTER
// TODO: i controller devono rispondere con i DTO
// TODO: scrivere i test con mockMVC per il controller
// TODO: scrivere anche tutti gli altri test

// TODO: studiare mapStruct con Lombock
// TODO: Riscrivere l'ATE di Cinematic.com con le varie API, Entity messe in fondo, DTO messi in fondo