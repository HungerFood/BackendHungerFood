FROM openjdk:17.0.2-jdk-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} HungerFoodBackend.jar
CMD apt-get update -y
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/HungerFoodBackend.jar"]