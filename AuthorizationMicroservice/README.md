# Authorization Microservice
Authorization service generates token for user credentials and validates that token for authentication.

## Requirements
For building and running the application you need,
  * JDK 1.8
  * Maven
  
## Steps to run
* Build the project using mvn clean install
* Run using mvn spring-boot:run
* The web application is accessible via localhost:8080/authapp
* Use username and password as 'admin' to login

## Steps to execute DB scripts
* No Physical database required. We use an In-memory database(H2) here.
