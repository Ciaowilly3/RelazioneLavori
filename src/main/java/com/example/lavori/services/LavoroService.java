package com.example.lavori.services;

import com.example.lavori.models.Lavoro;

import java.util.List;
import java.util.Optional;

public interface LavoroService {

    public void addLavoro(Lavoro lavoro);

    public List<Lavoro> getAllLavori();

    public Optional<Lavoro> getLavoroById(Long id);

    public void updateLavoro(Long idToUpdate, Lavoro lavoro);

    public void deleteLavoro(Long id);

    public List<Lavoro> findByUserName(String name);
}
