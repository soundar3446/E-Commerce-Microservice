FROM openjdk:17-jdk-slim as builder


WORKDIR /app


COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline


COPY src ./src

# Build the application using the Maven wrapper, creating the .jar file.

RUN ./mvnw package -DskipTests

FROM openjdk:17-jre-slim

# Set the working directory for the final image
WORKDIR /app

# Copy ONLY the final .jar file from the 'builder' stage into this image.
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

# The command that will be executed when the container starts.
# This runs your Spring Boot application.
ENTRYPOINT ["java","-jar","app.jar"]