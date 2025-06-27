FROM openjdk:17-jdk-slim as builder

# Set the current working directory inside the container
WORKDIR /app


COPY .mvn/ .mvn
COPY mvnw pom.xml ./


RUN ./mvnw dependency:go-offline

# Copy the rest of the source code into the container
COPY src ./src


RUN ./mvnw package -DskipTests


# ---- Stage 2: Create the final, smaller runtime image ----
# We use a much smaller JRE (Java Runtime Environment) image, since we
# only need to run the application, not build it.
FROM openjdk:17-jre-slim

# Set the working directory for the final image
WORKDIR /app

# Copy ONLY the final .jar file from the 'builder' stage into this image.
COPY --from=builder /app/target/*.jar app.jar


EXPOSE 8082


ENTRYPOINT ["java","-jar","app.jar"]