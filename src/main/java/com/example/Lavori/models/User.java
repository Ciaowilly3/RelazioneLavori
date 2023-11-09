package com.example.Lavori.models;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLavoro(Lavoro lavoro){
        this.lavoro = lavoro;
    }

    public String getId() {
        return userId;
    }
}
