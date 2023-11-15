package com.example.lavori.services.Impl;

import com.example.lavori.exceptions.InvalidSearchKeyException;
import com.example.lavori.exceptions.UsersByNameNotFoundException;
import com.example.lavori.models.Lavoro;
import com.example.lavori.models.User;
import com.example.lavori.repositories.LavoroRepository;
import com.example.lavori.repositories.UserRepository;
import com.example.lavori.services.UserService;
import com.example.lavori.utils.StringValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LavoroRepository lavoroRepository;

    public void addUser(String userName, String lavoroName){
        log.info("Start - addUser - args: user e lavoro {} {}", userName, lavoroName);
        var lavoro = lavoroRepository.findFirstByLavoroNameOrderByLavoroIdAsc(lavoroName);
        if (lavoro.isEmpty()) {
            lavoro = Optional.of(Lavoro.builder().lavoroName(lavoroName).build());
            lavoro.get().setLavoroId(UUID.randomUUID().toString());
            lavoroRepository.save(lavoro.get());
        }
        val user = new User();
        user.setName(userName);
        user.setLavoro(lavoro.get());
        log.info("End - addUser - out: {}", user);
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        log.info("Start - getAllUsers - args: none");
        val allUsers = userRepository.findAll();
        log.info("End - getAllUsers - args: none");
        return allUsers;
    }

    public void updateUser(String idToUpdate, User user) {
        log.info("Start - updateUser - args: id e user {} {} ", idToUpdate, user);
        userRepository.findById(idToUpdate)
                .map(u -> {
                    u.setName(user.getName());
                    userRepository.save(u);
                    log.info("End - updateUser - out {} ", u);
                    return u;
                });
    }

    public void deleteUser(String idToDelete){
        log.info("Start - deleteUser - args: id={}", idToDelete);
        val user = userRepository.findById(idToDelete);
        userRepository.deleteById(idToDelete);
        log.info("End - deleteUser - out: {}", user);
    }

    public Optional<User> getUserById(String id){
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

        // TODO: Creare interfacce services
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

