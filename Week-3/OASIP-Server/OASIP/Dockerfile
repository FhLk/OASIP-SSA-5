FROM maven:3.8.5-jdk-11 as build-stage
WORKDIR /tmp/ 
COPY . /tmp/
RUN mvn clean install -DskipTests

FROM openjdk:11
ARG JAR_FILE=target/OASIP-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENV TZ=Asia/Bangkok
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
