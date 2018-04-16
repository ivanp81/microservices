package it.joint.microservices.advertise.integrationTests.domain.service;

import it.joint.microservices.advertise.AdvertiseApplication;
import it.joint.microservices.advertise.domain.event.AdvertiseEvent;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.service.AdvertiseEventNotifier;
import it.joint.microservices.advertise.tests.mock.broker.DummyAdvertiseMessageConsumer;
import it.joint.microservices.advertise.tests.mock.broker.EmbeddedBrokerConfig;
import it.joint.microservices.advertise.tests.mock.broker.EmbeddedBrokerRule;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {AdvertiseApplication.class, EmbeddedBrokerConfig.class})
public class AdvertiseMessageProducerTest {

	private static final String DEFAULT_ID = "11111111";
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

    @ClassRule
    public static EmbeddedBrokerRule embeddedBrokerRule = new EmbeddedBrokerRule();

    @Autowired
    private AdvertiseEventNotifier advertiseMessageProducer;
        
    @Autowired
    private DummyAdvertiseMessageConsumer advertiseMessageConsumer;
    
    @Test
    public void testBroadCastSavedEvent() throws Exception {

    	advertiseMessageConsumer.initCounter();
    	Object message = new Advertise.Builder().withId(DEFAULT_ID)
    											.withTitle(DEFAULT_TITLE)
				   						        .withContent(DEFAULT_CONTENT)
				   						        .build();
    	
    	AdvertiseEvent event = AdvertiseEvent.SAVED;
    	advertiseMessageProducer.broadcastEvent(event, message);
    	Thread.sleep(3000);
    	
    	assertThat(advertiseMessageConsumer.getCounter(), equalTo(1));
    }
    
    @Test
    public void testBroadCastDeletedEvent() throws Exception {

    	advertiseMessageConsumer.initCounter();
    	Object message = new Advertise.Builder().withId(DEFAULT_ID)
				   						        .build();
    	
    	AdvertiseEvent event = AdvertiseEvent.DELETED;
    	advertiseMessageProducer.broadcastEvent(event, message);
    	Thread.sleep(3000);
    	
    	assertThat(advertiseMessageConsumer.getCounter(), equalTo(1));
    }
}
