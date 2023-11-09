package com.example.Lavori.services;

import com.example.Lavori.models.Lavoro;
import com.example.Lavori.models.User;
import com.example.Lavori.repositories.LavoroRepository;
import com.example.Lavori.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final LavoroRepository lavoroRepository;

    public UserServiceImpl(UserRepository userRepository, LavoroRepository lavoroRepository){
        this.userRepository = userRepository;
        this.lavoroRepository = lavoroRepository;
    }

    public User addUser(String userName, String lavoroName){
        Lavoro lavoro = lavoroRepository.findByLavoroName(lavoroName);
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
}
