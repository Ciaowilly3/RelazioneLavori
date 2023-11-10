package com.example.lavori.services;

import com.example.lavori.dto.TestGenerics;
import com.example.lavori.models.Lavoro;
import com.example.lavori.models.User;
import com.example.lavori.repositories.LavoroRepository;
import com.example.lavori.repositories.UserRepository;
import com.example.lavori.uties.LogginUties;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LavoroServiceImpl {

    private final UserRepository userRepository;

    private final LavoroRepository lavoroRepository;
    @Autowired
    public LavoroServiceImpl(LavoroRepository lavoroRepository, UserRepository userRepository){
        this.lavoroRepository = lavoroRepository;
        this.userRepository = userRepository;
    }

    public Lavoro addLavoro(Lavoro lavoro){return lavoroRepository.save(lavoro);}

    public List<Lavoro> getAllLavori(){return lavoroRepository.findAll();}

    public Optional<Lavoro> getLavoroById(String id){
        return lavoroRepository.findById(id);
    }

    public Lavoro updateLavoro(String idToUpdate, Lavoro lavoro){
        Optional<Lavoro> lavoroToUpdate = lavoroRepository.findById(idToUpdate);
        return lavoroToUpdate
                .map(l -> {
                    l.setLavoroName(lavoro.getLavoroName());
                    lavoroRepository.save(l);
                    return l;
                })
                .orElse(null);
    }

    public List<Lavoro> findByUserName(String name){
        val userList = userRepository.findByName(name);
        System.out.println(userList);
        val lavoroList = userList.stream().map(User::getLavoro).toList();
        return lavoroList;
    }


}
