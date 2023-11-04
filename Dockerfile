FROM openjdk:11-jre-slim

WORKDIR /opt/
ADD  app.jar /opt/pins-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -XshowSettings:vm\
                -Dinstrument=false \
                -Dspring.profiles.active=$PROFILE \
                -jar pins-0.0.1-SNAPSHOT.jar"]