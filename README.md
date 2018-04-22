# Advertise Microservices

This is a proof-of-concept application which demonstrates, against an hypothetical Microservice Architecture, the following: 
- architecture component containerization through Docker
- testing strategies

As the project focus is to provide containerization and testing strategies, the architecture don't comprise the classical infrastructure services needed by a Microservice Architecture (e.g. Service Discovery, Service Config, etc...) and isn't tied to a specific cloud solution (e.g. Spring Cloud, Kubernetes, AWS) 

Despite the premises, a pretty neat ReactJS user interface is provided.

#Domain
The following is the simple domain problem that the service try to solve:

A user can manage advertises. He can list, save, update, delete it. He can perform a full-text search inside the advertises's title and content. Every word researched must be present in the advertise.  
The search index is automatically synchronized every time that an event (save\update, delete) occurs on an advertise.  

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

# Testing
Following are the assumption made for testing:

- Unit tests are solitary (use Mocks) and perform behavior verification 
- Integration\Component tests are sociable (use some real collaborators), and perform state verification
- All the external system component should be runned as in memory process 
- Some test duplication are possible as we want see the testing strategies from different point of view

#### Framework
- Junit
- Mockito
- RestAssured
- Spring Test

#### In memory infrastructure
- Embedded MongoDB
- Embedded ElasticSearch
- QPID broker

## Run the test

#### Unit test  
```
$ mvn test
```

#### Integration\Component test
```
$ mvn verify
```
#### Acceptance test
```
$ mvn verify -Pacceptance-tests -Dacceptance.advertise.url=URL_TO_ADVERTISE_TESTING_ENVIRONMENT -Dacceptance.advertise-search.url=URL_TO_ADVERTISE_SEARCH_TESTING_ENVIRONMENT
```

# Build the project

#### Build the frontend
From folder microservices/advertise-ui:

```
$ npm run build
```

#### Build the backend
```
$ mvn clean package
```

#### Build Docker images
```
export SPRING_PROFILES_ACTIVE=*a spring profile (e.g testing, prod)*
export MONGODB_ADVERTISE_PASSWORD=*password for advertise db*
export MONGODB_ROOT_USERNAME=*root username for MongoDB*
export MONGODB_ROOT_PASSWORD=*root password for MongoDB*
export RABBITMQ_PASSWORD=*guest password for RabbitMQ*

$ docker-compose -f docker-compose.yml -f docker-compose.dev.yml build 
```

# How to run

#### Development mode 
```
run docker-compose -f docker-compose.yml -f docker-compose.dev.yml up
```
**docker-compose.dev.yml** inherits **docker-compose.yml** with additional possibility to build images locally and expose all containers ports for convenient development.

#### Production mode
```
run docker-compose up
```

# Important endpoints
- http://localhost:80 - UI
- http://localhost:8080 - Gateway
- http://localhost:8081 - Advertise microservice
- http://localhost:8082 - Advertise-search microservice
- http://localhost:15672 - RabbitMq management (default login/password: guest/guest)

# Reference
The following are some reference articles from which I took inspiration:
- https://martinfowler.com/articles/microservice-testing/
- https://martinfowler.com/articles/practical-test-pyramid.html
- https://martinfowler.com/articles/mocksArentStubs.html

# Feedback welcome
As this is a proof-of-concept mistake and miserunderstunding are possible. Every type of feedback is welcome to improve it.  