# Utilisez une image de base avec Java (par exemple, OpenJDK)
FROM openjdk:11

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app



# Exposez le port sur lequel votre application écoute
EXPOSE 8080

# Commande pour démarrer l'application
CMD ["java",  "examen"]
