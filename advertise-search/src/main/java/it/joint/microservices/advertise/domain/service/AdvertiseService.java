package it.joint.microservices.advertise.domain.service;

import java.util.List;

import it.joint.microservices.advertise.domain.model.Advertise;

public interface AdvertiseService {

    public List<Advertise> searchAdvertises(String query);
}
