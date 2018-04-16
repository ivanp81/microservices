#!/bin/bash 

export SPRING_PROFILES_ACTIVE=testing
export MONGODB_ADVERTISE_PASSWORD=advertise
export MONGODB_ROOT_USERNAME=root
export MONGODB_ROOT_PASSWORD=root
export RABBITMQ_PASSWORD = guest

docker stop advertise-mongodb
docker rm advertise-mongodb
docker rmi ivanp81/advertise-mongodb

docker stop microservices-rabbitmq
docker rm microservices-rabbitmq

docker stop advertise-index-elasticsearch
docker rm advertise-index-elasticsearch

docker stop advertise
docker rm advertise
docker rmi ivanp81/advertise

docker stop advertise-index
docker rm advertise-index
docker rmi ivanp81/advertise-index

mvn clean package
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up --build 