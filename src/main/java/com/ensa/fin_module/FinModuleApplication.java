package com.ensa.fin_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ensa.fin_module.repositories")  // Scanne les repositories dans ce package
public class FinModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinModuleApplication.class, args);
	}
}