package it.joint.microservices.advertise.unitTests.domain.service;

import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.joint.microservices.advertise.config.RabbitMQBroadcastConfig;
import it.joint.microservices.advertise.domain.event.AdvertiseEvent;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.service.AdvertiseEventNotifier;
import it.joint.microservices.advertise.domain.service.AdvertiseEventNotifierImpl;
import it.joint.microservices.advertise.util.JSONUtil;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdvertiseEventNotifierTest {

	private static final String DEFAULT_ID = "11111111";
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
	private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	private AdvertiseEventNotifier advertiseMessageProducer;

	@Mock
	private RabbitTemplate rabbitTemplate;

	@Before
	public void setUp() {

		initMocks(this);
		advertiseMessageProducer = new AdvertiseEventNotifierImpl(rabbitTemplate);
	}

	@Test
	public void testBroadCastSavedEvent() throws JsonProcessingException {

		Object message = new Advertise.Builder().withTitle(DEFAULT_TITLE).withContent(DEFAULT_CONTENT).build();

		AdvertiseEvent event = AdvertiseEvent.SAVED;
		advertiseMessageProducer.broadcastEvent(event, message);

		verify(rabbitTemplate).convertAndSend(RabbitMQBroadcastConfig.advertiseTopicExchangeName,
				"#." + event.getName(), JSONUtil.serializeToJson(message));
	}

	@Test
	public void testBroadCastDeletedEvent() throws JsonProcessingException {

		Object message = new Advertise.Builder().withId(DEFAULT_ID);
		AdvertiseEvent event = AdvertiseEvent.DELETED;

		advertiseMessageProducer.broadcastEvent(event, message);

		verify(rabbitTemplate).convertAndSend(RabbitMQBroadcastConfig.advertiseTopicExchangeName,
				"#." + event.getName(), JSONUtil.serializeToJson(message));
	}
}