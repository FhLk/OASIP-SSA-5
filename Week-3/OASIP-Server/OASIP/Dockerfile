FROM maven:3.8.5-jdk-11 as build-stage
RUN mkdir /OASIP-SSA5
WORKDIR /OASIP-SSA5
COPY . .
RUN mvn -Dmaven.test.skip package

FROM openjdk:11 as production-stage
COPY --from=build-stage /OASIP-SSA5/*.jar app.jar
ENV TZ=Asia/Bangkok
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
