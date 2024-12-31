package com.ensa.fin_module.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class YAMLData {

    @Id
    private Long id;  // Ajoute une clé primaire

    // Getters et Setters
    private String className;         // Nom de la classe pour la génération du code
    private String framework;         // Framework pour lequel générer le code (Spring Boot, React, etc.)

    @ElementCollection(fetch = FetchType.LAZY)  // Utilise ElementCollection pour persister la collection
    private List<Field> fields;       // Liste des champs de la classe

}
