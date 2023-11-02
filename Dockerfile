FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app/pom.xml
EXPOSE 8080
WORKDIR /app
RUN mvn clean package

FROM openjdk:17
COPY --from=maven-builder /app/target/pins-0.0.1-SNAPSHOT.jar /app/pins-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/app/pins-0.0.1-SNAPSHOT.jar"]

