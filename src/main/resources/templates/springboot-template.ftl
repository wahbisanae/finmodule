package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Entity
public class ${className} {

    <#list fields as field>
    private ${field.type} ${field.name};
    </#list>

    // Getters and setters
}
