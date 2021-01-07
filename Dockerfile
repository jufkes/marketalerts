FROM openjdk:16-slim

ADD target/marketalerts-0.0.1-SNAPSHOT.jar app.jar
ADD src/main/resources/application.yaml .

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=/application.yaml"]