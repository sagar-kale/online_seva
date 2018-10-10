# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Sagar Kale <sagark.kale@hotmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/online-seva-0.1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} online-seva-0.1.0.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/online-seva-0.1.0.jar"]

