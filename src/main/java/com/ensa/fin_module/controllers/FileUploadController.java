package com.ensa.fin_module.controllers;

import com.ensa.fin_module.services.FileProcessingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private final FileProcessingService fileProcessingService;
    private final ObjectMapper yamlMapper;

    public FileUploadController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("framework") String framework) {
        logger.info("Réception d'un fichier pour le framework : {}", framework);

        if (file.isEmpty()) {
            logger.warn("Fichier vide reçu");
            return ResponseEntity.badRequest().body("Le fichier est vide. Veuillez téléverser un fichier YAML valide.");
        }

        try {
            Object yamlData = yamlMapper.readValue(file.getInputStream(), Object.class);
            logger.info("Fichier YAML analysé avec succès.");

            if (!isFrameworkSupported(framework)) {
                logger.warn("Framework non pris en charge : {}", framework);
                return ResponseEntity.badRequest().body("Framework non pris en charge : " + framework);
            }

            // Process the YAML data to generate code
            String generatedCode = fileProcessingService.generateCode(yamlData, framework);
            logger.info("Code généré avec succès pour le framework : {}", framework);

            // Return the generated code as a response
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated-code.txt")
                    .body(generatedCode); // Send the generated code as the response body
        } catch (Exception e) {
            logger.error("Erreur lors du traitement du fichier YAML", e);
            return ResponseEntity.badRequest().body("Erreur lors du traitement du fichier YAML : " + e.getMessage());
        }
    }

    private boolean isFrameworkSupported(String framework) {
        return framework.equalsIgnoreCase("springboot") ||
                framework.equalsIgnoreCase("react") ||
                framework.equalsIgnoreCase("flask");
    }
}
