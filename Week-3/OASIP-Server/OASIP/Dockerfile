FROM maven:3.8.5-jdk-11 as build-stage
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn clean install -DskipTests

FROM openjdk:11 as production-stage
COPY --from=build-stage target/OASIP-0.0.1-SNAPSHOT.jar /usr/local/lib/OASIP-0.0.1-SNAPSHOT.jar
ENV TZ=Asia/Bangkok
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/OASIP-0.0.1-SNAPSHOT.jar"]
