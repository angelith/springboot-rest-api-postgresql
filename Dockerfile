FROM adoptopenjdk:11-jre-hotspot
MAINTAINER angelith
COPY target/rest-api-postgres-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]