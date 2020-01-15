FROM openjdk:8
MAINTAINER Daniel Bonaldo <dnllink@hotmail.com>
COPY ./target /var/www
WORKDIR /var/www
ENTRYPOINT java -jar trymee-backoffice-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080