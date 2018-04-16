package it.joint.microservices.advertise.componentTests.domain.service;

import it.joint.microservices.advertise.config.RabbitMQConfig;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.tests.mock.broker.EmbeddedBrokerRule;
import it.joint.microservices.advertise.tests.mock.elasticsearch.EmbeddedElasticSearchRule;
import it.joint.microservices.advertise.util.JSONUtil;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class AdvertiseBroadcastReceiverTest {

	private static final String DEFAULT_ID = "11111111";
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
	private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	@ClassRule
	public static EmbeddedBrokerRule embeddedBrokerRule = new EmbeddedBrokerRule();

	@ClassRule
	public static EmbeddedElasticSearchRule embeddedElasticSearchRule = new EmbeddedElasticSearchRule();

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AdvertiseRepository advertiseRepository;

	@Test
	public void testOnAdvertiseCreatedMessage() throws Exception {

		Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE)
				.withContent(DEFAULT_CONTENT).build();
		String advertiseSaved = JSONUtil.serializeToJson(advertise);
		rabbitTemplate.convertAndSend(RabbitMQConfig.advertiseSavedQueueName, advertiseSaved);

		Thread.sleep(3000);
		assertThat(advertiseRepository.findOne(DEFAULT_ID), equalTo(advertise));
	}

	@Test
	public void testOnAdvertiseDeletedMessage() throws Exception {

		Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE)
				.withContent(DEFAULT_CONTENT).build();
		advertiseRepository.save(advertise);

		String advertiseDeleted = JSONUtil.serializeToJson(advertise);
		rabbitTemplate.convertAndSend(RabbitMQConfig.advertiseDeletedQueueName, advertiseDeleted);

		Thread.sleep(3000);
		assertThat(advertiseRepository.findOne(DEFAULT_ID), equalTo(null));
	}
}
