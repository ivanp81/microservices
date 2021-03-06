# XP and DevOps in Microservices

This is a proof-of-concept which demonstrates, against an hypothetical Microservice Architecture, the following: 
- architecture components containerization through Docker
- testing strategies
- CI with Jenkins
- containers orchestration with Kubernetes

As the project focus is to provide some XP and DevOps practices, the architecture don't comprise the classical infrastructure services needed by a Microservice Architecture (e.g. Auth, Service Discovery, Service Config, etc...) and isn't tied to a specific cloud solution (e.g. Spring Cloud, Kubernetes, AWS).

Despite the premises, as I was interested in learn some ReactJS basics a user interface is provided.

# Domain
The following is the simple domain problem that the service try to solve:

- A user can manage advertises. An advertise have title and content. Users can list, save, update, delete advertise
- A user can perform a full-text search inside the advertises's title and content. Every word researched must be present in the advertise
- The search-index is automatically synchronized every time that an event (save\update, delete) occurs on an advertise

# Features
#### Frontend
- React JS
- React Router
- Redux
- Bootstrap         

#### Backend
- Spring Boot
- Lombock (for boilerplate code. See [here][lombock_setup] for setup instruction)
- MongoDB
- ElasticSearch
- RabbitMQ         

# Testing
Following are the assumption made for testing:

- Unit tests are solitary (use Mocks) and perform behavior verification 
- Integration\Component tests are sociable (use some real collaborators), and perform state verification
- As we want fast test and fast feedback, the external system component are executed with in memory process 
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
- http://localhost:15672 - RabbitMQ management


# What is missing

### Jenkins pipeline
You can notice the absence of a pipeline. 

I preferred to leave it outside of the topic of this project as every microservices I've developed in the various projects follow an identical DevOps pattern. This means that we can easily adapt the pipeline showed in the https://github.com/ivanp81/address-pipeline repository for the microservices in this project.  

The second reason is that I wanted to keep this project in one repostitory so that a reader can see everything together. Actually the pipeline is meant to have a repository per service. 

### Kubernetes orchestration
The same consideration are valid for Kubernetes. 

The pipeline and the microservice associated are entirely executed on Kubernetes. We can adapt the Kubernetes, service\deployment to the microservices showed in this project. 

Also, the intention at this stage of the project is to don't tye the architecture to a cloud solution.

Another reason is that, for more complex use case you need to bring up a pod for every component involved. This require a super powerfull laptop.    

# Evolution
- Add a security/authentication service to the architecture
- Try to run a microservices in AWS (EC2\ECS)

# Reference
The following are some reference articles from which I took inspiration:
- https://martinfowler.com/articles/microservice-testing/
- https://martinfowler.com/articles/practical-test-pyramid.html
- https://martinfowler.com/articles/mocksArentStubs.html

# Note
This is the most complete README. I need to collect my idea to write some about the pipeline.

# Feedback welcome
As this is a proof-of-concept mistake and misunderstanding are possible. Every type of feedback is welcome to improve it. 

[lombock_setup]: <https://projectlombok.org/setup/overview>
