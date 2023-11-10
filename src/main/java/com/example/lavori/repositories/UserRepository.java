package com.example.lavori.repositories;

import com.example.lavori.models.Lavoro;
import com.example.lavori.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByName(String name);
    List<User> findByLavoro(Lavoro lavoro);
}
