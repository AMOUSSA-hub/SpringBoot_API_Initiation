# Étape 1 : Build (builder)
FROM maven:3.9.11-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copier le POM et pré-télécharger les dépendances
COPY pom.xml .

# Copier le code source et compiler
COPY src ./src
RUN mvn clean package -X -e -DskipTests

# Étape 2 : Runtime léger
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]