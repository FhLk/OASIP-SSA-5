# build stage
FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
CMD [ "./mvnw clean install","./mnvw spring-boot:run" ]
