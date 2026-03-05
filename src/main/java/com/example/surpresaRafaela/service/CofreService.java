package com.example.surpresaRafaela.service;

import com.example.surpresaRafaela.model.Surpresa;
import com.example.surpresaRafaela.repository.SurpresaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CofreService {

    private final SurpresaRepository repository;

    public CofreService(SurpresaRepository repository) {
        this.repository = repository;
    }

    // Método que o React vai ficar chamando para ver se o cadeado já sumiu
    public String verificarStatusDoRelogio() {
        LocalDateTime dataLiberacao = LocalDateTime.of(2026, 3, 6, 0, 0); // Dia 6 de março de 2026, às 00:00

        if (LocalDateTime.now().isBefore(dataLiberacao)) {
            return "TRANCADO";
        }
        return "LIBERADO";
    }

    // Método que roda quando ela clica em "Destravar" e manda a senha
    public Surpresa tentarDesbloquear(String respostaDigitada) {

        // 1. A Regra do Tempo (Segurança dupla!)
        LocalDateTime dataLiberacao = LocalDateTime.of(2026, 3, 6, 0, 0);
        if (LocalDateTime.now().isBefore(dataLiberacao)) {
            throw new RuntimeException("Apressadinha! O cadeado só abre no dia 06/03.");
        }

        // 2. Busca a surpresa no MariaDB (ID 1, pois só inserimos uma linha)
        Surpresa surpresa = repository.findById(1)
                .orElseThrow(() -> new RuntimeException("Ops, ocorreu um erro ao buscar a carta no banco!"));

        // 3. A Regra da Senha
        if (!surpresa.getResposta().equalsIgnoreCase(respostaDigitada.trim())) {
            throw new RuntimeException("Resposta incorreta! Tente puxar na memória de novo...");
        }

        // Se passou pela data e pela senha... Entrega a carta!
        return surpresa;
    }
}