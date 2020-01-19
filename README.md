# Teaching-HEIGVD-AMT-2019-Project-Two
## Objectives

The objectives of this project is to design, specify, implement and validate a simple application that exposes a REST API allowing people to connect as user and find about Esport players and their teams.

The technologies that we used are:

* **Spring Boot**, **Spring Data**, **Spring MVC** and **Spring Data** for the implementation of the endpoints and of the persistence;
* **Swagger** (**Open API**) to create a formal documentation of the REST APIs (this formal documentation has to be used in the development cycle);
* JSON Web Tokens (**JWT**) to secure the RESTful endpoints;
* **CucumberJVM** to implement BDD tests.

## Description
Our application is composed of two backend REST services, one to manage differents users and one to manage Esport Teams and players.



## Functional requirements

* The first API  is used to manage User account.
 - Every user has an email, a first name, a last name , a role(admin or standard user) and a password.
 - Only an admin can create a new user.
 - a user can log himself to the application an is given a token to authorize him to reset his password or if he is an admin create a new user



* The second API is to manage the Esport team and players
 - The api support the CRUD operations to update players and teams.


## Non-functional requirements

* **Automation**
  * The application can be launched by executing the RunApplication.sh script and then going to localhost:8080/auth for the first API and localhost:8080/api  to access the second API.
* **Testing**
  * Due to a lack of time we havent implemented tests

* **Documentation**
