package com.example.Lavori.repositories;

import com.example.Lavori.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);
}
