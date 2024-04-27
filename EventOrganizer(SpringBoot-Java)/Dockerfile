
# Official OpenJDK runtime as a parent image
FROM openjdk:17-jdk

ARG WAR_FILE=target/*.war

COPY ${WAR_FILE} EventOrganizer.war

EXPOSE 9090

CMD ["java", "-jar", "/EventOrganizer.war"]