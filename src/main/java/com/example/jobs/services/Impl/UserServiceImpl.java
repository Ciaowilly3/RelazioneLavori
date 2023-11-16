package com.example.jobs.services.Impl;

import com.example.jobs.exceptions.InvalidSearchKeyException;
import com.example.jobs.exceptions.UsersByNameNotFoundException;
import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import com.example.jobs.repositories.JobRepository;
import com.example.jobs.repositories.UserRepository;
import com.example.jobs.services.UserService;
import com.example.jobs.utils.StringValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public void addUser(String userName, String JobName){
        log.info("Start - addUser - args: user e job {} {}", userName, JobName);
        var job = jobRepository.findFirstByJobNameOrderByJobIdAsc(JobName);
        if (job.isEmpty()) {
            job = Optional.of(Job.builder().jobName(JobName).build());
            jobRepository.save(job.get());
        }
        val user = new User();
        user.setName(userName);
        user.setJob(job.get());
        log.info("End - addUser - out: {}", user);
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        log.info("Start - getAllUsers - args: none");
        val allUsers = userRepository.findAll();
        log.info("End - getAllUsers - args: none");
        return allUsers;
    }

    public void updateUser(Long idToUpdate, User user) {
        log.info("Start - updateUser - args: id e user {} {} ", idToUpdate, user);
        userRepository.findById(idToUpdate)
                .map(u -> {
                    u.setName(user.getName());
                    userRepository.save(u);
                    log.info("End - updateUser - out {} ", u);
                    return u;
                });
    }

    public void deleteUser(Long idToDelete){
        log.info("Start - deleteUser - args: id={}", idToDelete);
        val user = userRepository.findById(idToDelete);
        userRepository.deleteById(idToDelete);
        log.info("End - deleteUser - out: {}", user);
    }

    public Optional<User> getUserById(Long id){
        log.info("Start - getUserById - args: id={}", id);
        val user = userRepository.findById(id);
        log.info("Start - getUserById - out:{}", user);
        return user;

    }
    public String retrieveSerializedNamesByUserName(String searchKey){
        log.info("Start - getUserByChar - args: searchKey={}", searchKey);
        if (!StringValidationUtils.areOnlyLetters(searchKey)){
            throw new InvalidSearchKeyException("not valid input");
        }
        val  userList = userRepository.findByNameStartingWith(searchKey);
        if (userList.isEmpty()){
            throw new UsersByNameNotFoundException("no record found");
        }
        val users = new StringBuilder();
        for (val user : userList) {
            users.append(user.getName()).append(", ");
        }
        log.info("End - getUserByChar - out: {}", users);
        return users.substring(0, users.length()-2);
    }
    public String retrieveSerializedNamesByUserNamev2(String searchKey){
        log.info("Start - getUserByChar - args: searchKey={}", searchKey);
        if (!StringValidationUtils.areOnlyLetters(searchKey)){
            throw new InvalidSearchKeyException("not valid input");
        }
        val  userList = userRepository.findNamesByNameStartingWith(searchKey);
        if (userList.isEmpty()){
            throw new UsersByNameNotFoundException("no record found");
        }
        val users = new StringBuilder();
        for (val user : userList) {
            users.append(user).append(", ");
        }
        log.info("End - getUserByChar - out: {}", users);
        return users.substring(0, users.length()-2);
    }

    public String retrieveSerializedNamesByUserNamev3(String searchKey) {
        log.info("Start - getUserByChar - args: searchKey={}", searchKey);
        if (!StringValidationUtils.areOnlyLetters(searchKey)) {
            throw new InvalidSearchKeyException("not valid input");
        }
        val userList = userRepository.findNamesByNameStartingWith(searchKey);
        if (userList.isEmpty()) {
            throw new UsersByNameNotFoundException("no record found");
        }
        return String.join(", ", userList);
    }
}

