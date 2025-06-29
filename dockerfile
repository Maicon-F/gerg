FROM openjdk:22-jdk-slim

WORKDIR /app

COPY solution/target/app-2.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

