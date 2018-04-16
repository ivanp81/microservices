package it.joint.microservices.advertise.domain.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.joint.microservices.advertise.config.RabbitMQConfig;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.util.JSONUtil;

@Component
public class AdvertiseBroadcastReceiverImpl implements AdvertiseBroadcastReceiver {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private AdvertiseRepository advertiseRepository;
	
	@Autowired
	public AdvertiseBroadcastReceiverImpl(AdvertiseRepository advertiseRepository) {
		this.advertiseRepository = advertiseRepository;
	}
	
	@RabbitListener(queues = RabbitMQConfig.advertiseSavedQueueName)
    public void onAdvertiseCreatedMessage(String advertiseSaved) {
		log.info("onAdvertiseCreatedMessage " + advertiseSaved);
		try {
			Advertise advertise = JSONUtil.deserializeToObject(advertiseSaved);
	        advertiseRepository.save(advertise);
		} catch (IOException e) {	
			// TODO to improve
			log.error("onAdvertiseCreatedMessage error > can't process ", advertiseSaved);			
		}
    }
	
	@RabbitListener(queues = RabbitMQConfig.advertiseDeletedQueueName)
    public void onAdvertiseDeletedMessage(String advertiseDeleted) {
		log.info("onAdvertiseDeletedMessage " + advertiseDeleted);
		try {
			Advertise advertise = JSONUtil.deserializeToObject(advertiseDeleted);
	        advertiseRepository.delete(advertise);
		} catch (IOException e) {
			// TODO to improve
			log.error("onAdvertiseDeletedMessage error > can't process ", advertiseDeleted);					
		}
    }
}
