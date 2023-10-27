package com.fernandes.br.universidade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class ModelAluno {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID matricula;
    private String nome;
    private String email;
}
