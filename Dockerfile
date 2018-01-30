FROM tomcat:8.0.47-jre8

LABEL maintainer="chris.ruegger@gmail.com"

COPY /build/libs/restful-jersey.war /usr/local/tomcat/webapps

EXPOSE 8080
