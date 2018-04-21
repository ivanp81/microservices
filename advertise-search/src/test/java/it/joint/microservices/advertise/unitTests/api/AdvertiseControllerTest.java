package it.joint.microservices.advertise.unitTests.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import it.joint.microservices.advertise.api.AdvertiseController;
import it.joint.microservices.advertise.domain.service.AdvertiseService;

public class AdvertiseControllerTest {

	private static final String QUERY = "AAA";

	private AdvertiseController advertiseController;

	@Mock
	private AdvertiseService advertiseService;

	@Before
	public void setUp() {

		initMocks(this);
		advertiseController = new AdvertiseController(advertiseService);
	}

	@Test
	public void testGetAdvertise() {

		advertiseController.searchAdvertises(QUERY);
		verify(advertiseService).searchAdvertises(QUERY);
	}
}