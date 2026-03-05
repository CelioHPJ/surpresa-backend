package com.example.surpresaRafaela.repository;

import com.example.surpresaRafaela.model.Surpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurpresaRepository extends JpaRepository<Surpresa, Integer> {
    // Vazio mesmo! O JpaRepository já tem o método findById() pronto.
}