
FROM openjdk:8-jdk-alpine
EXPOSE 9096
COPY /target/packsendme-payment-server-0.0.1-SNAPSHOT.jar packsendme-payment-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/packsendme-payment-server-0.0.1-SNAPSHOT.jar"]