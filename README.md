# Project setup

In order to start the application you need to follow these steps:

### I. Database

**Prerequisites**: To be able to run docker-compose commands, you need to have docker and docker-compose installed.

To start the PostgreSQL database, you need to run the `docker-compose -f docker-compose.yaml up` command in the root folder. If you want to start the database in detached mode you could provide the **-d** flag.

### II. Spring Boot application

**Prerequisites**: To be able to run the application, you need to have java installed.

For starting the application you can use your IDEs built-in solutions (in IntelliJ IDEA you just need to click on the Run button) or you could run the application from terminal with the `./mvnw spring-boot:run` command from the root folder.

#### Note: don't forget to start the database along with the application!