# Étape 1 : Construction avec Maven
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app
# Copier les fichiers de configuration Maven
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copier le code source et construire l'application
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image finale
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copier le fichier JAR depuis l'étape précédente
COPY --from=builder /app/target/fin-module-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port de l'application
EXPOSE 8082

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
