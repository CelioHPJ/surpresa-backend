package com.example.surpresaRafaela.controller;

import com.example.surpresaRafaela.model.Surpresa;
import com.example.surpresaRafaela.service.CofreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cofre")
public class CofreController {

    private final CofreService cofreService;

    public CofreController(CofreService cofreService) {
        this.cofreService = cofreService;
    }

    // O React vai chamar essa rota a cada segundo para atualizar a tela
    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> checarStatus() {
        String status = cofreService.verificarStatusDoRelogio();
        // Devolvemos um JSON simples: {"status": "TRANCADO"} ou {"status": "LIBERADO"}
        return ResponseEntity.ok(Map.of("status", status));
    }

    // A rota que recebe a tentativa de senha
    @PostMapping("/desbloquear")
    public ResponseEntity<?> desbloquear(@RequestBody Map<String, String> request) {
        try {
            // Pega a senha que veio do React no formato JSON: {"senha": "praca"}
            String senhaDigitada = request.get("senha");

            // Manda o Serviço processar. Se der erro (senha errada ou antes do dia 6), ele cai no catch
            Surpresa surpresa = cofreService.tentarDesbloquear(senhaDigitada);

            // Se deu tudo certo, devolve a carta e as fotos!
            return ResponseEntity.ok(surpresa);

        } catch (RuntimeException e) {
            // Se ela errar a senha ou tentar abrir hoje, devolvemos o erro 400 (Bad Request)
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}