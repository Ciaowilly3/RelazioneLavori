package com.example.lavori.services.Impl;

import com.example.lavori.models.Lavoro;
import com.example.lavori.models.User;
import com.example.lavori.repositories.LavoroRepository;
import com.example.lavori.repositories.UserRepository;
import com.example.lavori.services.LavoroService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LavoroServiceImpl implements LavoroService {

    private final UserRepository userRepository;

    private final LavoroRepository lavoroRepository;
    @Autowired
    public LavoroServiceImpl(LavoroRepository lavoroRepository, UserRepository userRepository){
        this.lavoroRepository = lavoroRepository;
        this.userRepository = userRepository;
    }

    public void addLavoro(Lavoro lavoro){
        log.info("Start - addLavoro - args: lavoro= {}", lavoro);
        log.info("Start - addLavoro - out:{}", lavoro);
        lavoroRepository.save(lavoro);
    }

    public List<Lavoro> getAllLavori(){
        log.info("Start - getAllLavori - args: none");
        val allLavori = lavoroRepository.findAll();
        log.info("End - getAllLavori - out: {}", allLavori);
        return allLavori;
    }

    public Optional<Lavoro> getLavoroById(Long id){
        log.info("Start - getLavoroById - args: id={}", id);
        val lavoro = lavoroRepository.findById(id);
        log.info("End - getLavoroById - out: {}", lavoro);
        return lavoro;
    }

    public void updateLavoro(Long idToUpdate, Lavoro lavoro){
        log.info("Start - updateLavoro - args: id e lavoro {} {}", idToUpdate, lavoro);
        Optional<Lavoro> lavoroToUpdate = lavoroRepository.findById(idToUpdate);
        lavoroToUpdate
                .map(l -> {
                    l.setLavoroName(lavoro.getLavoroName());
                    lavoroRepository.save(l);
                    log.info("End - updateLavoro - out: {}", l);
                    return l;
                });
    }

    public void deleteLavoro(Long id){
        log.info("Start - deleteLavoro - args: id={}", id);
        val lavoroToDelete = lavoroRepository.findById(id);
        if (lavoroToDelete.isEmpty()){
            return;
        }
        val lavoro = lavoroToDelete.get();
        val usersTodelete = userRepository.findByLavoro(lavoro);
        usersTodelete.forEach(userRepository::delete);
        lavoroRepository.delete(lavoro);
        log.info("End - deleteLavoro - out: {}", lavoro);
    }

    public List<Lavoro> findByUserName(String name){
        log.info("Start - findByUserName - args: name={}", name);
        val userList = userRepository.findByName(name);
        val user = userList.stream().map(User::getLavoro).toList();
        log.info("End - findByUserName - out: {}", user);
        return user;
    }
}
