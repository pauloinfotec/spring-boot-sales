# Spring Boot project developed to implement JUnit/Mockito tests using Rest Assured framework

## Read the instructions how to do project configuration

### Before started

Make sure you have installed:
* Java: JDK version 8+ [(download)](https://www.oracle.com/technetwork/pt/java/javase/overview/index.html)
* IDE: [Intellij IDEA](https://www.jetbrains.com/idea/) | [Eclipse](https://www.eclipse.org/downloads/) | [Netbeans](https://netbeans.org/) | ... 
* Apache Maven [(download)](https://maven.apache.org/)
* Cliente Git [(download)](https://git-scm.com/downloads)

### Tech Details

This project use [Spring Boot](https://spring.io/projects/spring-boot) and it was built using [http://start.spring.io](http://start.spring.io).
This project was configured to use database [H2](http://www.h2database.com).

#### How to run project

Find class **Application** (br.com.prss.sales.Application) and run method **main**. Access your browser:

[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

if you see **{"status":"UP"}**, means that everything worked out.

#### How to run tests

Open a command line window (terminal) and go to the project root directory and execute:

mvn test

### Have a fun!
