FROM maven:3.8.2-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

### package ###
FROM openjdk:17-alpine
COPY --from=build /home/app/target/menu-0.0.1-SNAPSHOT.jar /usr/local/lib/menu.jar
CMD java -jar /usr/local/lib/menu.jar --server.port=$PORT