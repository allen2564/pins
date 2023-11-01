FROM adoptopenjdk:17-jdk-hotspot AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/pins-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "pins-0.0.1-SNAPSHOT.jar"]