package com.example.lavori.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "u1_0")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "lavoro_id", nullable = false)
    private Lavoro lavoro;
    public User() { this.userId = UUID.randomUUID().toString(); }

    public User(String name, String userId) {
        this.userId = userId;
        this.name = name;
    }

}
