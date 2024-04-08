# Utilisez une image de base avec Java (par exemple, OpenJDK)
FROM openjdk:11

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/mon-application.jar .

# Exposez le port sur lequel votre application écoute
EXPOSE 8080

# Commande pour démarrer l'application
CMD ["java", "-jar", "mon-application.jar"]
