## **Order service**

Order service is a part of microservices application Online store. It manages orders.
Spring Controller (OrderController) class exposes the HTTP endpoints that can be invoked on the microservice.

## **Building**

To compile source code and build Docker image:
```
mvn clean package docker:build
```

## **Running**

To start service in Docker container:
```
docker run marinasavchenko/onlinestore-ordersrv:v1
```

## **Running the tests**

To run tests via Maven:
```
mvn clean test
```

## **Technology stack**

* Java
* Spring Boot
* Spring Cloud
* Spring Data

* jUnit
* Mockito

* Maven
* Docker