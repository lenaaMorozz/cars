FROM tomcat:10.1.13-jdk17
EXPOSE 8080
COPY ROOT.war /usr/local/tomcat/webapps/
#COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
#
#RUN chmod +x /usr/local/bin/wait-for-it.sh

ENV MYSQL_HOST=db
ENV MYSQL_PORT=3306

CMD ["catalina.sh", "run"]