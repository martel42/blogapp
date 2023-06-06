FROM maven:3.9.2-eclipse-temurin-17-alpine AS BUILD
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17
COPY --from=build /home/app/target/Gateway-0.0.1-SNAPSHOT.jar /usr/local/lib/gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/gateway.jar"]
