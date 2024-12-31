package com.ensa.fin_module.models;


import jakarta.persistence.Embeddable;

@Embeddable  // Indique que Field peut être embarqué dans une autre entité
public class Field {

    private String name;    // Nom du champ
    private String type;    // Type du champ (par exemple, String, int, etc.)

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
