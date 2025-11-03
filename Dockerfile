# Step 1: Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy the JAR file from target folder into the container
COPY target/*.jar app.jar

# Step 4: Expose port 8080
EXPOSE 8080

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
