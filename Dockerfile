FROM openjdk:26-trixie

ADD target/memoire-api.jar memoire-api.jar

ENTRYPOINT ["java", "-jar","memoire-api.jar"]