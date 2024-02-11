FROM openjdk:17-alpine
MAINTAINER ASPD <aspd@email.com>

RUN mkdir /home/reaction-service
COPY target/reaction-service-0.0.1-SNAPSHOT.jar /home/reaction-service/

ENTRYPOINT ["java","-jar","/home/reaction-service/reaction-service-0.0.1-SNAPSHOT.jar"]