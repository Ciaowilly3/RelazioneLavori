package com.example.jobs.repositories;

import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByJob(Job job);
    List <User> findByNameStartingWith(String filters);

    @Query("SELECT u.name FROM User u WHERE u.name LIKE :searchKey%")
    List<String> findNamesByNameStartingWith(@Param("searchKey") String searchKey);

}
