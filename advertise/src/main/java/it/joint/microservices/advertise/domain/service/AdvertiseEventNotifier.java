package it.joint.microservices.advertise.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.joint.microservices.advertise.domain.event.AdvertiseEvent;

public interface AdvertiseEventNotifier {

	public void broadcastEvent(AdvertiseEvent event, Object message) throws JsonProcessingException;
}