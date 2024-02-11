FROM openjdk:17-alpine
MAINTAINER ASPD <aspd@email.com>

RUN mkdir /home/comment-service
COPY target/comment-service-0.0.1-SNAPSHOT.jar /home/comment-service

ENTRYPOINT ["java","-jar","/home/comment-service/comment-service-0.0.1-SNAPSHOT.jar"]