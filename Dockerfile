FROM tomcat:8.0
LABEL maintainer="alex1990"
ARG WAR_FILE=target/*.war
COPY ${WAR_FILE} user-service.war
COPY department-service/target/department-service.war /usr/local/tomcat/webapps/