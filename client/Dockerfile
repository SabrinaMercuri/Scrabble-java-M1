FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/client-1-spring-boot.jar
COPY ${JAR_FILE} client.jar
ENTRYPOINT java -jar /client.jar --serveurIP=172.28.0.253