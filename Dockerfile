# Seleccionar una imagen base con Java 17 y Spring Boot
FROM rsunix/yourkit-openjdk17


# Definir el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/pins-0.0.1-SNAPSHOT.jar /app

# Exponer el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando de inicio para ejecutar la aplicación
CMD ["java", "-jar", "pins-0.0.1-SNAPSHOT.jar"]
