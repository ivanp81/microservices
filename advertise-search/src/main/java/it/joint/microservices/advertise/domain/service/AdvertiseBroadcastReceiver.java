package it.joint.microservices.advertise.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import it.joint.microservices.advertise.config.RabbitMQConfig;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.utils.JSONUtil;

@Component
public class AdvertiseBroadcastReceiver {

	private final Logger log = LoggerFactory.getLogger(AdvertiseBroadcastReceiver.class);

	private AdvertiseRepository advertiseRepository;
	
	@Autowired
	public AdvertiseBroadcastReceiver(AdvertiseRepository advertiseRepository) {
		this.advertiseRepository = advertiseRepository;
	}
	
	@RabbitListener(queues = RabbitMQConfig.advertiseSavedQueueName)
    public void onAdvertiseCreatedMessage(String advertiseSaved) {
		log.debug("Advertise saved message");
		Advertise advertise = JSONUtil.derializeToObject(advertiseSaved);
        advertiseRepository.save(advertise);
    }
	
	@RabbitListener(queues = RabbitMQConfig.advertiseDeletedQueueName)
    public void onAdvertiseDeletedMessage(String advertiseDeleted) {
		log.debug("Advertise deleted message");
		Advertise advertise = JSONUtil.derializeToObject(advertiseDeleted);
        advertiseRepository.delete(advertise);
    }
}
