FROM openjdk:17
COPY target/auth-0.0.1-SNAPSHOT.jar /usr/local/lib/stats.jar
EXPOSE 8079
ENTRYPOINT ["java","-jar","/usr/local/lib/stats.jar"]
