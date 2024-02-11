FROM openjdk:17-alpine
MAINTAINER ASPD <aspd@email.com>

RUN mkdir /home/blog-service
COPY target/blog-service-0.0.1-SNAPSHOT.jar /home/blog-service

ENTRYPOINT ["java","-jar","/home/blog-service/blog-service-0.0.1-SNAPSHOT.jar"]