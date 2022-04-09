FROM openjdk:11
ARG JAR_FILE=logingRequest/target/logingRequest-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} logingRequest.jar
EXPOSE 8511
ENTRYPOINT ["java","-jar","/logingRequest.jar"]
