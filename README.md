##**Returning people living in and present around London**

###Calling the service
1. _mvn clean spring-boot:run_ - to run the application
2. Call: _curl -X GET "http://localhost:8080/users/city/London"_

The result is a list of users in London and 50 miles around London. The distance is configurable in **application.properties**
The base url and connection/read timeouts are also configurable in **application.properties**

###Compiling and running the tests
1. _mvn clean install_ - to compile and run the tests

_Note: I have copied the formulae to calculate the distance between 2 coordinates from: https://www.geodatasource.com/developers/java_