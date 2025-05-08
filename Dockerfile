FROM openjdk:21-jdk
COPY build/libs/HHH_BE-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]