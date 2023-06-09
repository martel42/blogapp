FROM openjdk:17
COPY target/Gateway-0.0.1-SNAPSHOT.jar /usr/local/lib/gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/gateway.jar"]
