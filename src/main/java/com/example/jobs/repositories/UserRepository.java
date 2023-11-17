package com.example.jobs.repositories;

import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);
    List<User> findByJob(Job job);
    List <User> findByUserNameStartingWith(String filters);

//    Long findUserIdByUserName(String userName);

    @Query("SELECT u.userName FROM User u WHERE u.userName LIKE :searchKey%")
    List<String> findUserNamesByNameStartingWith(@Param("searchKey") String searchKey);

}
