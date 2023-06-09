FROM openjdk:17
COPY target/subs-0.0.1-SNAPSHOT.jar /usr/local/lib/stats.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/usr/local/lib/stats.jar"]
