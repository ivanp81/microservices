package it.joint.microservices.advertise.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.joint.microservices.advertise.config.RabbitMQBroadcastConfig;
import it.joint.microservices.advertise.domain.event.AdvertiseEvent;
import it.joint.microservices.advertise.util.JSONUtil;

@Service
public class AdvertiseEventNotifierImpl implements AdvertiseEventNotifier {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private RabbitTemplate rabbitTemplate;

	@Autowired
	public AdvertiseEventNotifierImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void broadcastEvent(AdvertiseEvent event, Object message) throws JsonProcessingException {
		log.info("broadcastEvent " + event.getName().toUpperCase() + " with message " + message);
		rabbitTemplate.convertAndSend(RabbitMQBroadcastConfig.advertiseTopicExchangeName, "#." + event.getName(),
				JSONUtil.serializeToJson(message));
	}
}