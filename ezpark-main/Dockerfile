FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
# Copy everything
COPY . .
# Build modules in explicit dependency order
RUN mvn -B -e clean install -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/application/target/*.jar ezpark.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ezpark.jar"]