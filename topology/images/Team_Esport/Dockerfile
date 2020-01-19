FROM openjdk:8-jdk-alpine

COPY application/swagger-spring*.jar esportManager.jar
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "userManager.jar"]
