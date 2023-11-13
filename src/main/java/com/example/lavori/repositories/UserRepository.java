package com.example.lavori.repositories;

import com.example.lavori.models.Lavoro;
import com.example.lavori.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByName(String name);
    List<User> findByLavoro(Lavoro lavoro);
    List <User> findByNameStartingWith(String filters);

    @Query("SELECT u.name FROM User u WHERE u.name LIKE :searchKey%")
    List<String> findNamesByNameStartingWith(@Param("searchKey") String searchKey);
}
