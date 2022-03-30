FROM openjdk:11
ARG JAR_FILE=server/target/server-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} server.jar
EXPOSE 8511
ENTRYPOINT ["java","-jar","/server.jar"]
