# MarketLogic
This repository contains two projects :
1. Onesearch service : For searching the content in project snapshots
2. Project service : For CRUD operations,fetching and publishing the project

## Demo
I have prepared a recorded demo for this project, you can watch it here : 
[![MarketLogic Demo](http://img.youtube.com/vi/iMTfObbL5u0/0.jpg)](http://www.youtube.com/watch?v=iMTfObbL5u0 "MarketLogic Demo")

## Build : 
For both the projects, in the root directory run :
```
mvn clean package
```

## Run :
For both the projects, in their respective the root directory run :
```
java -jar target/onesearch-service-1.0.jar //for onesearch service
java -jar target/project-service-1.0.jar   //for project service
```
## URLs :
Swagger and h2 console are enabled for easy access to the endpoints and the database in each service.
### Swagger
The swagger ui can be accessed without any credentials; but to make the requests from swagger, authentication is requried.
1. Project service : http://localhost:8080/swagger-ui/
2. Search service : http://localhost:8081/swagger-ui/

### H2
Username and password for H2 console is mentioned in application.properties file.
1. Project service : http://localhost:8080/h2-console/
2. Project service : http://localhost:8081/h2-console/

## Scalability :
Multiple instances of onesearch service can be spawned and these instances can be referred in project service at runtime as:
```
--onesearch.ribbon.listOfServers=http://localhost:8081,http://localhost:8082
```
To reduce complexity in running these applications I have not enabled service discovery, but ribbon will take care of load balancing with above property.
#### As we are using in memory database, the data on multiple instances of onesearch service will not be the same.

## Availability : 
Project service calls the onesearch service endpoint as an async task. Using spring retry, project service will keep on retrying to connect to onesearch service as per following properties in project service :
```
app.max-attempts= 5
app.backoff-delay = 10000
app.backoff-delay-multiplier = 5
```
Even after the retries if the request fails, then an entry is made into a new table for failed requests. For sake of reducing complexity a scheduler is not written to pick the failed requests and try again. But this can be easily achieved.

## Security : 
To limit complexity of running these projects, all URLs except for swagger and h2-console are basic auth secured. Credentials are defined in application.properties as : 
```
//For project service
app.user-name=john
app.password=password

//For onesearch service
app.user-name=jane
app.password=password
```
The rest calls from project service to onesearch service uses these credentials as well.

## Application Properties :
All the properties in these microservices can be changed at startup. For example if you want to update the port you can pass new port number as --server.port =9000.

If you are changing the username/password/port in onesearch service, make sure to pass them as runtime argument to project service.

### Project Service
```
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
app.enable-security=true
app.user-name=john
app.password=password
app.onesearch-username=jane
app.onesearch-password=password
app.max-attempts= 1
app.backoff-delay = 10000
app.backoff-delay-multiplier = 5
spring.jackson.serialization.INDENT_OUTPUT=true
onesearch.ribbon.listOfServers=http://localhost:8081
spring.jpa.properties.hibernate.show_sql=true
```
### Onesearch Service
```
server.port=8081
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
app.enable-security = true
app.user-name=jane
app.password=password
spring.jackson.serialization.INDENT_OUTPUT=true
spring.jpa.properties.hibernate.show_sql=true
```
