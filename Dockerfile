FROM tomcat:10.1.13-jdk17
EXPOSE 8080
COPY ROOT.war /usr/local/tomcat/webapps/
ENV MYSQL_HOST=db
ENV MYSQL_PORT=3306

CMD ["catalina.sh", "run"]