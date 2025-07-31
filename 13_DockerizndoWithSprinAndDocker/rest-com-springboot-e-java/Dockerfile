FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
LABEL authors="Marcelo Farias"
ENTRYPOINT ["java","-jar","/app.jar"]