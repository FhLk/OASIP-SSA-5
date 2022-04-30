# build stage
FROM openjdk:11
RUN ./mvnw clean install
RUN ./mnvw spring-boot:run
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

