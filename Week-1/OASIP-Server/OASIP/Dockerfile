FROM maven:3.8.5-jdk-11 as build-stage
WORKDIR /tmp/ 
COPY . /tmp/
RUN mvn -Dmaven.test.skip package

FROM openjdk:11
ARG JAR_FILE=target/OASIP-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]