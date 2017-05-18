Exchange Rates

Simple Java Web Monolit Application

Technologies used:

1. Spring 4 / File configuration
2. Hibernate 4
3. JSF 2.2
4. Primefaces 6.1
5. Apache Http Client
6. MySQL
7. H2 in memory database.
8. Lombok
9. Liquibase
10. Ehcache
11. Apache Tomcat 8/9
12. Java SE 8

Application is monolit , but backend logic in separated  jar, which will allow you change Spring configuration to Spring boot and add REST layer for integration with frontend.

In the current application architecture you can find how to use Spring and JSF together and develop rich web application.

I have video sharing web application developed with microservices architecture https://github.com/armdev/tosptube , which is use also Youtube API


How to install exchange-rates:

1. Preconditions: Java 8, Maven, MySQL 5, Apache Tomcat 8
2. cd rates-db-migrator, edit pom.xml if your database host, port, user pass is different.
3. setup database: mvn clean package -U 
4. cd rates-backend, mvn clean package -U , it will execute also unit tests
5. cd exchange-rates, mvn clean package -U
6. cd rates-web/target , copy rates-web.war to apache-tomcat\webapps
7. Start Tomcat: apache-tomcat/bin/catalina.sh start , or apache-tomcat/bin/run.bat
8. Navigate to http://localhost:(tomcat-port))/rates-web


Application build pipline history located here https://gitlab.com/armdev/exchange-rates/pipelines







