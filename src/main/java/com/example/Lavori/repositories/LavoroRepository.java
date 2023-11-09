package com.example.Lavori.repositories;

import com.example.Lavori.models.Lavoro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LavoroRepository extends JpaRepository<Lavoro, String> {
    Lavoro findByLavoroName(String lavoroName);
}
