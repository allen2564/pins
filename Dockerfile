# Seleccionar una imagen base con Java 17 y Maven
FROM adoptopenjdk:17-jdk-hotspot AS build

# Definir el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y compilar la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Crear una imagen final con la aplicación compilada
FROM adoptopenjdk:17-jre-hotspot

# Definir el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación desde la etapa de compilación
COPY --from=build /app/target/pins-0.0.1-SNAPSHOT.jar .

# Exponer el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando de inicio para ejecutar la aplicación
CMD ["java", "-jar", "pins-0.0.1-SNAPSHOT.jar"]