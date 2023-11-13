package com.example.lavori.repositories;

import com.example.lavori.models.Lavoro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LavoroRepository extends JpaRepository<Lavoro, String> {
    Optional<Lavoro> findFirstByLavoroNameOrderByLavoroIdAsc(String lavoroName);
}
