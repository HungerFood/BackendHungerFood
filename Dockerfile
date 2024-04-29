FROM openjdk:22-jdk-oracle
VOLUME [ "/tmp" ]
EXPOSE 8080
ARG JAR_FILE=target/BackendHungerFood.jar
ADD ${JAR_FILE} BackendHungerFood.jar
ENTRYPOINT ["java","-jar","/BackendHungerFood.jar"]