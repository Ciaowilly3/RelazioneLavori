package com.example.lavori.services;

import com.example.lavori.models.Lavoro;
import com.example.lavori.models.User;
import com.example.lavori.repositories.LavoroRepository;
import com.example.lavori.repositories.UserRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final LavoroRepository lavoroRepository;

    public UserServiceImpl(UserRepository userRepository, LavoroRepository lavoroRepository){
        this.userRepository = userRepository;
        this.lavoroRepository = lavoroRepository;
    }

    public User addUser(String userName, String lavoroName){
        val lavoroList = lavoroRepository.findByLavoroName(lavoroName);
        var lavoro = lavoroList.get(0);
        if (lavoro == null){
            lavoro = new Lavoro();
            lavoro.setLavoroName(lavoroName);
            lavoroRepository.save(lavoro);
        }
        User user = new User();
        user.setName(userName);
        user.setLavoro(lavoro);
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){return userRepository.findAll();}

    public User updateUser(String idToUpdate, User user) {
        Optional<User> userToUpdate = userRepository.findById(idToUpdate);
        return userToUpdate
                .map(u -> {
                    u.setName(user.getName());
                    userRepository.save(u);
                    return u;
                })
                .orElse(null);
    }
}

