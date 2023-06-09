FROM openjdk:17
COPY target/user-0.0.1-SNAPSHOT.jar /usr/local/lib/stats.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/usr/local/lib/stats.jar"]
