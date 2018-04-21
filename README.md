# Advertise Microservices

This is a proof-of-concept application, which demonstrates Microservice Architecture Pattern using Spring Boot. 

The architecture don't comprise the classical infrastructure service needed by this type of architecture (e.g. Service Discovery, Service Config, etc...) and is not tied to a specific cloud solution (e.g. Spring Cloud, Kubernetes, AWS) 

This choose come by the fact that the main purpose of the project is:

- show a polyglot persistence and asynchronous service-to-service communication via Message broker
- perform various type of test, in particular use an in memory infrastructure in the integration test

Every component of the architecture is containerized through Docker.

A pretty neat ReactJS user interface is provided.

# Features

#### Frontend
- React JS
- React Router
- Redux
- Bootstrap         

#### Backend
- Spring Boot
- MongoDB
- ElasticSearch
- RabbitMQ         

# Test
#### Framework
- Junit
- Mockito
- RestAssured
- Spring Test

### In memory infrastructure
- Embedded MongoDB
- Embedded ElasticSearch
- QPID broker

# How to run

mvn verify -Pacceptance-tests -Dacceptance.advertise.url=http://localhost:8080 -Dacceptance.advertise-search.url=http://localhost:8081
