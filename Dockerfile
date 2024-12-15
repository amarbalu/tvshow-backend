


 # Backend Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY target/tvshow-backend-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources /app/src/main/resources
EXPOSE 8080
# Run the application and log database details
 CMD ["sh", "-c", "java -jar app.jar"]

