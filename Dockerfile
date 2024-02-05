FROM openjdk:17-alpine

# Arg used to store the location of the .jar file
ARG APPFILE=target/*.jar

COPY ${APPFILE} /natwest.jar
ENTRYPOINT ["sh", "-c", "java -jar natwest.jar"]