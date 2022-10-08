FROM openjdk:8
EXPOSE 8081
ADD target/App4Docker.jar App4Docker.jar
ENTRYPOINT ["java","-jar","/App4Docker.jar"]
USER root
RUN apt-get update && apt-get -qy full-upgrade && apt-get install -qy curl && curl -sSL https://get.docker.com/ | sh