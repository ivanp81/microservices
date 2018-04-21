#!/bin/bash 

export SPRING_PROFILES_ACTIVE=testing
export MONGODB_ADVERTISE_PASSWORD=advertise
export MONGODB_ROOT_USERNAME=root
export MONGODB_ROOT_PASSWORD=root
export RABBITMQ_PASSWORD=guest

docker stop advertise-gateway
docker rm advertise-gateway
docker rmi ivanp81/advertise-gateway

docker stop advertise-mongodb
docker rm advertise-mongodb
docker rmi ivanp81/advertise-mongodb

docker stop advertise-rabbitmq
docker rm advertise-rabbitmq

docker stop advertise-search-elasticsearch
docker rm advertise-search-elasticsearch

docker stop advertise
docker rm advertise
docker rmi ivanp81/advertise

docker stop advertise-search
docker rm advertise-search
docker rmi ivanp81/advertise-search

cd ui && npm run build --production
cd .. && mvn clean package -DskipTests
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up --build 