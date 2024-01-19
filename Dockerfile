FROM alpine:latest
USER root

RUN apk update
RUN apk fetch openjdk17
RUN apk add openjdk17
COPY target/SpringAuthorization-0.0.1-SNAPSHOT.jar /usr/local/SpringAuthorization-0.0.1-SNAPSHOT.jar
WORKDIR /usr/local/
EXPOSE 8080
CMD ["java", "-jar", "SpringAuthorization-0.0.1-SNAPSHOT.jar"]