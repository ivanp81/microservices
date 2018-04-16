package it.joint.microservices.advertise.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import it.joint.microservices.advertise.domain.model.Advertise;

@Repository
public interface AdvertiseRepository extends ElasticsearchRepository<Advertise, String> {
}
