package com.example.surpresaRafaela.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "surpresa")
public class Surpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String pergunta;

    @Column(nullable = false, length = 500)
    private String resposta;

    @Column(nullable = false, length = 1000)
    private String mensagem;
}