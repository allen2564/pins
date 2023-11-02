# Usamos una imagen base con Maven y Java 17
FROM adoptopenjdk:17-jre-hotspot as base

# Definimos variables de entorno para configurar la aplicación
ENV APP_HOME=/app
ENV APP_JAR=pins-0.0.1-SNAPSHOT.jar

# Directorio de trabajo para la construcción de la aplicación
WORKDIR $APP_HOME

# Copiamos el archivo pom.xml y las dependencias
COPY ./app/pom.xml ./pom.xml
COPY ./app/src ./src

# Empaquetar la aplicación Spring Boot
RUN mvn -B -DskipTests clean package
RUN mv target/*.jar $APP_JAR

# Etapa de construcción final
FROM base

# Directorio de trabajo para la aplicación
WORKDIR $APP_HOME

# Copiamos el archivo JAR construido desde la etapa anterior
COPY --from=base $APP_HOME/$APP_JAR $APP_HOME/$APP_JAR

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "pins-0.0.1-SNAPSHOT.jar"]
