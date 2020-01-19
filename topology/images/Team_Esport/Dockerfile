FROM openjdk:8-jdk-alpine

COPY application/userManager*.jar userManager.jar
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "userManager.jar"]
