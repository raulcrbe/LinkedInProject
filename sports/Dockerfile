### DOCKERFILE MULTISTAGE ###

#Build stage
FROM gradle:jdk21-alpine AS builder
COPY build.gradle settings.gradle ./
COPY src src
RUN gradle clean build -x test

#Run stage
FROM openjdk:21-jdk
LABEL maintainer="raulcrbe <raulcrbe@gmail.com>"
EXPOSE 8080
COPY --from=builder /home/gradle/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]