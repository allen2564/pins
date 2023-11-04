# Etapa de construcción: Utiliza una imagen base con JDK 11 y Maven para compilar el proyecto
FROM maven:3.8.4-openjdk-17 AS build

# Establece un directorio de trabajo
WORKDIR /app

# Copia el archivo POM y los archivos de configuración de Maven
COPY pom.xml .

# Copia el resto de los archivos del proyecto
COPY src src

# Compila y empaqueta el proyecto
RUN mvn clean package -DskipTests

# Etapa de producción: Utiliza una imagen base de OpenJDK 11 para ejecutar la aplicación
FROM openjdk:22-slim-bullseye

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR construido desde la etapa de construcción
COPY --from=build /app/target/pins-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que utilizará la aplicación
EXPOSE 8080

# Establece las opciones de JVM para la aplicación (ajusta según sea necesario)
ENV JAVA_OPTS=""

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
