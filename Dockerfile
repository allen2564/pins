FROM  openjdk:22-slim-bullseye

WORKDIR /opt/
ADD  app.jar /opt/app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -XshowSettings:vm\
                -Dinstrument=false \
                -Dspring.profiles.active=$PROFILE \
                -jar app.jar"]