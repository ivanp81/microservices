package it.joint.microservices.advertise.unitTests.domain.service;

import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.domain.service.AdvertiseBroadcastReceiver;
import it.joint.microservices.advertise.domain.service.AdvertiseBroadcastReceiverImpl;
import it.joint.microservices.advertise.util.JSONUtil;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdvertiseBroadcastReceiverTest {

	private static final String DEFAULT_ID = "11111111";
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	private AdvertiseBroadcastReceiver advertiseBroadcastReceiver;
   
    @Mock
    private AdvertiseRepository advertiseRepository;
	
    @Before
    public void setUp() {
    	
        initMocks(this);
        advertiseBroadcastReceiver = new AdvertiseBroadcastReceiverImpl(advertiseRepository);
    }
    
    @Test
    public void testOnAdvertiseCreatedMessage() throws JsonProcessingException {

    	Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID)
    												 .withTitle(DEFAULT_TITLE)
				   						             .withContent(DEFAULT_CONTENT)
				   						             .build();
    	
    	advertiseBroadcastReceiver.onAdvertiseCreatedMessage(JSONUtil.serializeToJson(advertise));
    	
        verify(advertiseRepository).save(advertise);
    }

    @Test
    public void testBroadCastDeletedEvent() throws JsonProcessingException {

    	Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).build();
    	advertiseBroadcastReceiver.onAdvertiseDeletedMessage(JSONUtil.serializeToJson(advertise));
    	
    	verify(advertiseRepository).delete(advertise);
    }
}