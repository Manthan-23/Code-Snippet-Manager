
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM maven:3.9.6-eclipse-temurin-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19-jdk-slim-buster
COPY --from=build /target/major-0.0.1-SNAPSHOT.jar major.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","major.jar"]

