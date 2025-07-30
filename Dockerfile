# Stage 1: Build the application
FROM gradle:jdk20-jammy AS build
WORKDIR /home/gradle/src

COPY . .
RUN gradle buildUberJar --no-daemon

# Stage 2: Create the runtime image
FROM openjdk:20-slim
WORKDIR /app
COPY .env /app/.env
COPY --from=build /home/gradle/src/build/libs/college_backend-all.jar /app/college_backend.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "college_backend.jar"]