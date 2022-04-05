FROM openjdk:11
ARG JAR_FILE=client/target/client-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} client.jar
EXPOSE 8411
ENTRYPOINT ["java","-jar","/client.jar"]
