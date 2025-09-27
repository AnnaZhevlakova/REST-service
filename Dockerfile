
FROM openjdk:23-jdk-slim



ADD target/REST-service-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 5500


CMD ["java", "-jar", "app.jar"]