FROM maven:3.9.14-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

EXPOSE 8080

COPY --from=builder /app/target/ArduinoBackend-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]