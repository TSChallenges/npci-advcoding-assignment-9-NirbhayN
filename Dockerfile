# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Override application properties at runtime
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://docker.for.mac.host.internal:5432/storedb

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.datasource.url=${SPRING_DATASOURCE_URL}"]
