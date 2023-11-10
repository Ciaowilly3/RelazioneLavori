package com.example.lavori.repositories;

import com.example.lavori.models.Lavoro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LavoroRepository extends JpaRepository<Lavoro, String> {
    List<Lavoro> findByLavoroName(String lavoroName);
}
