package com.ensa.fin_module.services;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Map;

@Service
public class CodeGenerationService {


        private final Configuration freemarkerConfig;

        public CodeGenerationService(Configuration freemarkerConfig) {
            this.freemarkerConfig = freemarkerConfig;
        }

        /**
         * Génère le code source basé sur les données YAML et le framework spécifié.
         *
         * @param yamlData Données YAML sous forme de Map.
         * @param framework Nom du framework pour lequel générer le code.
         * @return Le code généré sous forme de chaîne.
         */
        public String generateCode(Map<String, Object> yamlData, String framework) {
            switch (framework.toLowerCase()) {
                case "springboot":
                    return generateForSpringBoot(yamlData);
                case "react":
                    return generateForReact(yamlData);
                case "flask":
                    return generateForFlask(yamlData);
                default:
                    throw new IllegalArgumentException("Framework non pris en charge : " + framework);
            }
        }

        private String generateForSpringBoot(Map<String, Object> yamlData) {
            return generateFromTemplate("springboot-template.ftl", yamlData);
        }

        private String generateForReact(Map<String, Object> yamlData) {
            return generateFromTemplate("react-template.ftl", yamlData);
        }

        private String generateForFlask(Map<String, Object> yamlData) {
            return generateFromTemplate("flask-template.ftl", yamlData);
        }

        /**
         * Génère du code en utilisant un modèle FreeMarker.
         *
         * @param templateName Nom du fichier de modèle FreeMarker.
         * @param yamlData     Données à injecter dans le modèle.
         * @return Le code généré.
         */
        private String generateFromTemplate(String templateName, Map<String, Object> yamlData) {
            try {
                Template template = freemarkerConfig.getTemplate(templateName);
                StringWriter writer = new StringWriter();
                template.process(yamlData, writer);
                return writer.toString();
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la génération à partir du modèle : " + e.getMessage(), e);
            }
        }
    }


