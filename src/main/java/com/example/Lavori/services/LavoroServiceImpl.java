package com.example.Lavori.services;

import com.example.Lavori.models.Lavoro;
import com.example.Lavori.models.User;
import com.example.Lavori.repositories.LavoroRepository;
import com.example.Lavori.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public Lavoro findByUserName(String name){
        User user = userRepository.findByName(name);
        Lavoro lavoro = user.getLavoro();
        return lavoroRepository.findByLavoroName(lavoro.getLavoroName());
    }


}
