package it.joint.microservices.advertise.domain.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.service.AdvertiseEventNotifier;

@Component
public class AdvertiseMongoEventListener extends AbstractMongoEventListener<Advertise> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private AdvertiseEventNotifier advertiseEventNotifier;

	@Autowired
	public AdvertiseMongoEventListener(AdvertiseEventNotifier advertiseEventNotifier) {
		this.advertiseEventNotifier = advertiseEventNotifier;
	}

	@Override
	public void onAfterSave(AfterSaveEvent<Advertise> event) {
		log.info("onAfterSave event");
		try {
			advertiseEventNotifier.broadcastEvent(AdvertiseEvent.SAVED, event.getSource());
		} catch (JsonProcessingException e) {
			// TODO to improve
			log.error("onAfterSave error > can't process event source", event.getSource());
		}
	}

	@Override
	public void onAfterDelete(AfterDeleteEvent<Advertise> event) {
		log.info("onAfterDelete event");
		try {
			advertiseEventNotifier.broadcastEvent(AdvertiseEvent.DELETED, event.getSource());
		} catch (JsonProcessingException e) {
			// TODO to improve
			log.error("onAfterSave error > can't process event source " + event.getSource());
		}
	}
}