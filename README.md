# Advertise Microservices

This is a proof-of-concept application, which demonstrates various type of test in an hypothetical Microservice Architecture.  

As the project focus is provide a testing strategies the architecture don't comprise the classical infrastructure services needed by this type of architecture (e.g. Service Discovery, Service Config, etc...) and is not tied to a specific cloud solution (e.g. Spring Cloud, Kubernetes, AWS) 

Every component of the architecture is containerized through Docker.

Despite the premises, a pretty neat ReactJS user interface is provided.

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

#### In memory infrastructure
- Embedded MongoDB
- Embedded ElasticSearch
- QPID broker

# Build the project

#### Build the frontend
From folder microservices/advertise-ui:
```
$ npm run build
```

#### Build the backend
From folder microservices:
```
$ mvn clean package
```

#### Build Docker images
From folder microservices:
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

# Conclusion
As this project is a work in progress, further extension of the system could be:
- Add a security system (e.g. Oauth2)
- Decide the Cloud infrastructure where run the project
- Integrate the build and testing step in a pipeline 