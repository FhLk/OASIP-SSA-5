FROM maven:3.8.5-jdk-11 as build-stage
WORKDIR /app
COPY pom.xml .
RUN mvnw clean
COPY src ./src
RUN mvnw install


FROM openjdk:11 as production-stage
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]