FROM openjdk:22-jdk-oracle
VOLUME [ "/tmp" ]
EXPOSE 8080
ARG JAR_FILE=target/backend.jar
ADD ${JAR_FILE} backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]