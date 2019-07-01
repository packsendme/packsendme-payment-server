
FROM java:8
EXPOSE 9095
ADD /target/packsendme-payment-server-0.0.1-SNAPSHOT.jar packsendme-payment-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/packsendme-payment-server-0.0.1-SNAPSHOT.jar"]