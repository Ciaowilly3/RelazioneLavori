package com.example.Lavori.services;

import com.example.Lavori.models.Lavoro;
import com.example.Lavori.repositories.LavoroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LavoroServiceImpl {

    private final LavoroRepository lavoroRepository;
    @Autowired
    public LavoroServiceImpl(LavoroRepository lavoroRepository){this.lavoroRepository = lavoroRepository;}

    public Lavoro addLavoro(Lavoro lavoro){return lavoroRepository.save(lavoro);}

    public List<Lavoro> getAllLavori(){return lavoroRepository.findAll();}


}
