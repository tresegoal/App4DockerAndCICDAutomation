FROM openjdk:8
EXPOSE 8081
ADD target/App4Docker.jar App4Docker.jar
ENTRYPOINT ["java","-jar","/App4Docker.jar"]