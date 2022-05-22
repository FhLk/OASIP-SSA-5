FROM maven:3.8.5-jdk-11 as build-stage
RUN mkdir -p /spring-build
WORKDIR /spring-build
COPY pom.xml /spring-build
COPY src /spring-build/src
RUN mvn clean install package -DskipTests

FROM openjdk:11 as production-stage
ARG JAR_FILE=target/OASIP-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENV TZ=Asia/Bangkok
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
