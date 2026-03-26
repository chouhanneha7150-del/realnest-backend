FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build project
RUN ./mvnw clean package -DskipTests

# Debug (optional but helpful)
RUN ls target

# Run app (IMPORTANT FIX)
CMD ["sh", "-c", "java -jar target/*.jar"]