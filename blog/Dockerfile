FROM openjdk:17
COPY target/blog-0.0.1-SNAPSHOT.jar /usr/local/lib/stats.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/usr/local/lib/stats.jar"]
