# Etapa de construcción: Utiliza una imagen base con JDK 17 y Maven para compilar el proyecto
FROM maven:3.8.4-openjdk-17 AS build

# Argumento para decidir si eliminar imágenes antiguas
ARG DELETE_OLD_IMAGES=true

# Elimina las imágenes antiguas si DELETE_OLD_IMAGES=true
RUN if [ "$DELETE_OLD_IMAGES" = "true" ]; then \
    docker rmi $(docker images -q) || true; \
fi

# Establece un directorio de trabajo
WORKDIR /pin

# Copia el archivo POM y los archivos de configuración de Maven
COPY pom.xml .

# Copia el resto de los archivos del proyecto
COPY src src

# Compila y empaqueta el proyecto
RUN mvn clean package -DskipTests

# Etapa de producción: Utiliza una imagen base de OpenJDK 17 para ejecutar la aplicación
FROM openjdk:22-slim-bullseye

# Establece el directorio de trabajo
WORKDIR /pin

# Copia el archivo JAR construido desde la etapa de construcción
COPY --from=build /pin/target/pins-0.0.2.jar pines.jar

# Expone el puerto que utilizará la aplicación
EXPOSE 8080

# Establece las opciones de JVM para la aplicación (ajusta según sea necesario)
ENV JAVA_OPTS=""

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -XshowSettings:vm \
                -Dinstrument=false \
                -Dspring.profiles.active=$PROFILE \
                -jar pines.jar"]
