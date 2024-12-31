package com.ensa.fin_module.controllers;

import com.ensa.fin_module.services.CodeGenerationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/code")
public class CodeGenerationController {

    private final CodeGenerationService codeGenerationService;
    private final ObjectMapper yamlMapper;

    public CodeGenerationController(CodeGenerationService codeGenerationService) {
        this.codeGenerationService = codeGenerationService;
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
    }

    @PostMapping(value = "/generate", consumes = "multipart/form-data")
    public ResponseEntity<String> generateCodeFromFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("framework") String framework) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Le fichier est vide. Veuillez téléverser un fichier YAML valide.");
        }

        try {
            // Lire le contenu du fichier YAML
            Map<String, Object> yamlData = yamlMapper.readValue(file.getInputStream(), Map.class);

            // Appeler le service pour générer le code
            String generatedCode = codeGenerationService.generateCode(yamlData, framework);
            return ResponseEntity.ok(generatedCode);

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erreur de lecture du fichier YAML : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Framework non pris en charge : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la génération de code : " + e.getMessage());
        }
    }
}
