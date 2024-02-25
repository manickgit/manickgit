#Start with base image containing JVM runtime
FROM openjdk:17-jdk-slim

MAINTAINER eazybytes.com

# Add the appln. jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the appln
ENTRYPOINT ["java", "-jar","accounts-0.0.1-SNAPSHOT.jar"]