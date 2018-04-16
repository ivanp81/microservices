package it.joint.microservices.advertise.unitTests.api;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import it.joint.microservices.advertise.api.AdvertiseSearchController;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdvertiseControllerTest {

	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	private AdvertiseSearchController advertiseController;
   
    @Mock
    private AdvertiseRepository advertiseRepository;
    
    private Advertise advertise;
    
    @Before
    public void setUp() {
    	
        initMocks(this);
        advertiseController = new AdvertiseSearchController(advertiseRepository);
        advertise = new Advertise.Builder().withId("1")
        								   .withTitle(DEFAULT_TITLE)
        								   .withContent(DEFAULT_CONTENT)
        								   .build();
    }
        
    @Test
    public void givenValidAdvertiseId_whenGetAdvertise_thenReturnAdvertise() {

    	doReturn(advertise).when(advertiseRepository).findOne(anyString());
    	
        Advertise advertise = advertiseController.getAdvertise("1");
        
        verify(advertiseRepository).findOne(anyString());
        assertThat(advertise, equalTo(advertise));
    }
}