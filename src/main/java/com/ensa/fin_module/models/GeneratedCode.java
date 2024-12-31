package com.ensa.fin_module.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Setter
@Getter
@Entity
public class GeneratedCode {

    @Id
    private Long id;    // Identifiant unique pour chaque code généré
    // Getters et Setters
    private String framework;   // Le framework pour lequel le code a été généré
    private String code;        // Le code source généré

    // Constructeur
    public GeneratedCode() {}

    public GeneratedCode(String framework, String code) {
        this.framework = framework;
        this.code = code;
    }

}
