# 🛠️ **YAML to Code : Générateur Automatique de Code**

![image](https://github.com/user-attachments/assets/93eb1dc3-f665-458f-bf16-5b10269d8736)

YAML2Code est une application open-source innovante permettant la génération automatique de code pour des frameworks populaires tels que Spring Boot, React.js, et Flask à partir de fichiers YAML. L'objectif est de réduire le temps de développement, d'améliorer la productivité et de garantir une architecture robuste et standardisée pour vos projets.
---

## 📚 **Table des Matières**

- [🛠️ Architecture Logicielle](#-architecture-logicielle)  
- [🐳 Docker](#-docker)  
- [🎨 Frontend](#-frontend)  
- [💻 Backend](#-backend)  
- [🚀 Démarrage Rapide](#-démarrage-rapide)  
- [🎥 Démonstration Vidéo](#-démonstration-vidéo)  
- [🔑 Utilisation](#-utilisation)  
- [🤝 Contribuer](#-contribuer)  
- [🧑‍💻 Équipe de Développement](#-équipe-de-développement)  

---

## 🛠️ **Architecture Logicielle**


L'architecture de YAML2Code repose sur une approche modulaire :

- Frontend : React.js pour une interface utilisateur interactive.
- Backend : Spring Boot pour le traitement des fichiers YAML et la génération du code.
- Base de données : H2 (en mémoire) pour le stockage temporaire des configurations.
- API RESTful : Communication entre le frontend et le backend.

---

## 🐳 **Docker**

### **Exemple de docker-compose.yml :**
```
services:
  backend:
    build:
      context: C:\\Users\\ACCENT W5000\\Desktop\\fin-module
      dockerfile: Dockerfile
    container_name: inspring_hugle
    ports:
      - "8082:8082"
    environment:
      SPRING_DATASOURCE_URL: jdbc:h2:mem:yaml
      SPRING_DATASOURCE_DRIVERCLASSNAME: org.h2.Driver
      SPRING_DATASOURCE_USERNAME: sa
      SPRING_DATASOURCE_PASSWORD: ""
      SPRING_H2_CONSOLE_ENABLED: "true"
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_JPA_SHOW_SQL: "true"
      SERVER_PORT: 8082
      SPRING_FREEMARKER_PREFIX: classpath:/templates/
      SPRING_FREEMARKER_SUFFIX: .ftl
      SPRING_FREEMARKER_CACHE: "false"
      SPRING_SERVLET_MULTIPART_ENABLED: "true"
      SPRING_SERVLET_MULTIPART_MAX_FILE_SIZE: 2MB
      SPRING_SERVLET_MULTIPART_MAX_REQUEST_SIZE: 2MB
    networks:
      - mynetwork

  frontend:
    build:
      context: C:\\Users\\ACCENT W5000\\Desktop\\fin-module\\frontend
      dockerfile: Dockerfile
    container_name: beautiful_liskov
    ports:
      - "3000:80"
    depends_on:
      - backend
    environment:
      - REACT_APP_API_URL=http://backend:8082
    networks:
      - mynetwork

networks:
  mynetwork:
    driver: bridge
```

## Frontend

### Technologies Used

- React.js
- CSS
- Axios
  
### Structure du Frontend :
 - /src/components/ : Principals pages.
 - /src/assets/ : images for header.
   
### Fonctionnalités Clés :

 - Upload YAML file. 
 - choose the framwork.
 - Visualisation the generated code .

## Backend

### Technologies Used

- Spring Boot

### Backend Project Structure

   The backend code follows a modular and organized structure, leveraging the power of Spring Boot for building a robust and scalable application.

### 1. com.ensa.fin_module.FinModuleApplication

- *Main Application Class:* Application.java serves as the entry point for the Spring Boot application. It includes the main method to start the application.

### 2. com.ensa.fin_module.controllers

- *Controller Classes:* The controller package contains classes responsible for handling incoming HTTP requests. Each controller class is dedicated to a specific feature or entity, exposing RESTful endpoints. These classes interact with the services to process requests and return appropriate responses.


### 3. com.ensa.fin_module.models
- *Entity Classes:* The model package includes classes representing data entities in the application. These classes are annotated with JPA annotations, defining the structure of the database tables. Each entity typically corresponds to a table in the MySQL database.

### 4. com.ensa.fin_module.repositories

- *Repository Interfaces:* The repository package contains interfaces that extend Spring Data JPA repositories. These interfaces provide methods for basic CRUD operations and are used by services to interact with the database.

### 5. com.ensa.fin_module.services 

- This package contains business classes (services) responsible for the application logic.
-Services act as an intermediary layer between controllers and repositories.

### 6. com.ensa.fin_module.config 

- It allows servers to specify who can access their resources, under what conditions, and which HTTP methods are allowed.

### Dependencies

1. *Spring Data JPA:*
   - Purpose: Simplifies data access using JPA in Spring Boot.
2. H2 console:
Purpose: JDBC driver for connecting to a H2 database.

xml
```sh
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

## 🚀 **Getting Started**

Here are step-by-step instructions to set up and run your project locally.

### 📝 **Prerequisites:**

1. **Git:**  
   - Make sure Git is installed. If not, download and install it from [git-scm.com](https://git-scm.com/).

2. **Node.js and NVM:**  
   - Install Node Version Manager (NVM) from [github.com/nvm-sh/nvm](https://github.com/nvm-sh/nvm).
   - Use NVM to install the recommended Node.js version (v14.11.0) with the following command:  
     ```bash
     nvm install 14.11.0
     ```

3. **Java 17:**  
   - Make sure you have **Java 17** or a higher version installed on your machine. If not, download it from [oracle.com](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or install it using your preferred package manager.

4. **Docker (optional but recommended):**  
   - If you wish to use Docker for an easier setup, you can install Docker Desktop from [docker.com](https://www.docker.com/products/docker-desktop).

---

### ⚙️ **Backend Installation:**

1. **Clone the Project:**  
   Clone the GitHub repository to your local machine with the following command:
   ```bash
   git clone <repository_url>
   cd yaml-to-code-backend
'''
   

2. *Install Backend Dependencies:*
   - Open a terminal in the backend project folder.
   - Run the following commands:
     bash
     mvn clean install
     

3. *Run Backend:*
   - Run the Spring Boot application. The database and entities will be created automatically.
   - Verify that the backend is running by visiting [test in Postman: http://localhost:8082/api/code/generate]
      ((http://localhost:3000/)) in your browser.
### Frontend Setup:

1. *Install Node.js :*
   - Open a new terminal for the frontend project.
   - Ensure NVM is using Node.js version 14.11.0: nvm use 14.11.0.

2. *Install Frontend Dependencies:*
   - Run the following commands in the frontend project folder:
     bash
     npm install
 - If you encounter errors during installation, use the following command:
     bash
     npm install --save --legacy-peer-deps
     

3. *Run Frontend:*
   - After installing dependencies, start the Node.js development server:
     bash
     npm start
     

   - Access the frontend at [http://localhost:3000] in your browser.

Now, your full-stack project should be up and running locally. If you encounter any issues during setup, check the console logs for error messages and ensure that all dependencies and prerequisites are correctly installed.

# Video Demonstration

https://github.com/user-attachments/assets/a53677da-00a8-4836-aced-291633937843

# Contributing

We welcome contributions from everyone, and we appreciate your help to make this project even better! If you would like to contribute, please follow these guidelines:

## Contributors
- Sanae Wahbi ([GitHub](https://github.com/wahbisanae))
- Chaymae Aheddach ([GitHub](https://github.com/Aheddach))

