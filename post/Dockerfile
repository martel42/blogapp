FROM openjdk:17
COPY target/post-0.0.1-SNAPSHOT.jar /usr/local/lib/stats.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/usr/local/lib/stats.jar"]
