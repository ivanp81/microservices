package it.joint.microservices.advertise.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.joint.microservices.advertise.domain.model.Advertise;

@Repository
public interface AdvertiseRepository extends MongoRepository<Advertise, String> {
}
