FROM maven:3.9.2-eclipse-temurin-17-alpine AS BUILD
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17
COPY --from=build /home/app/target/Stats-0.0.1-SNAPSHOT.jar /usr/local/lib/stats.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/stats.jar"]
