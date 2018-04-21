package it.joint.microservices.advertise.unitTests.event;

import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import it.joint.microservices.advertise.domain.event.AdvertiseEvent;
import it.joint.microservices.advertise.domain.event.AdvertiseMongoEventListener;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.service.AdvertiseEventNotifier;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.HashMap;
import java.util.Map;

public class AdvertiseMongoEventListenerTest {

    private static final String DEFAULT_ID = "11111111";
    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

    private AdvertiseMongoEventListener advertiseMongoEventListener;

    @Mock
    private AdvertiseEventNotifier advertiseEventNotifier;

    @Before
    public void setUp() {
	initMocks(this);
	advertiseMongoEventListener = new AdvertiseMongoEventListener(advertiseEventNotifier);
    }

    @Test
    public void testOnAfterSave() throws JsonProcessingException {

	Map<String, String> mapAdvertise = new HashMap<>();
	mapAdvertise.put("title", DEFAULT_TITLE);
	mapAdvertise.put("content", DEFAULT_CONTENT);
	DBObject advertiseDbo = new BasicDBObject(mapAdvertise);
	Advertise advertise = new Advertise.Builder().withTitle(DEFAULT_TITLE).withContent(DEFAULT_CONTENT).build();
	AdvertiseEvent event = AdvertiseEvent.SAVED;
	AfterSaveEvent<Advertise> afterSaveEvent = new AfterSaveEvent<Advertise>(advertise, advertiseDbo, "advertises");

	advertiseMongoEventListener.onAfterSave(afterSaveEvent);

	verify(advertiseEventNotifier).broadcastEvent(event, advertise);
    }

    @Test
    public void testBroadCastDeletedEvent() throws JsonProcessingException {

	Map<String, String> mapAdvertise = new HashMap<>();
	mapAdvertise.put("id", DEFAULT_ID);
	DBObject advertiseDbo = new BasicDBObject(mapAdvertise);
	AdvertiseEvent event = AdvertiseEvent.DELETED;
	AfterDeleteEvent<Advertise> afterDeleteEvent = new AfterDeleteEvent<Advertise>(advertiseDbo, Advertise.class,
		"advertises");

	advertiseMongoEventListener.onAfterDelete(afterDeleteEvent);

	verify(advertiseEventNotifier).broadcastEvent(event, advertiseDbo);
    }
}