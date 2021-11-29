#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG DEPENDENCY=target/dependency
#COPY ${JAR_FILE} app.jar
##COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
##COPY ${DEPENDENCY}/META-INF /app/META-INF
##COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","src/main/java/com/restaurant/authentication/AuthenticationApplication.java"]


#FROM openjdk:8-jdk-alpine
#COPY target/docker-message-server-1.0.0.jar message-server-1.0.0.jar
#ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]