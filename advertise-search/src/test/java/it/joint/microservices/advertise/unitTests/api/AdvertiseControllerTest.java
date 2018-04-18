package it.joint.microservices.advertise.unitTests.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import it.joint.microservices.advertise.api.AdvertiseController;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;

public class AdvertiseControllerTest {

	private static final String DEFAULT_ID = "11111111";

	private AdvertiseController advertiseController;

	@Mock
	private AdvertiseRepository advertiseRepository;

	@Before
	public void setUp() {

		initMocks(this);
		advertiseController = new AdvertiseController(advertiseRepository);
	}

	@Test
	public void testGetAdvertise() {

		advertiseController.getAdvertise(DEFAULT_ID);
		verify(advertiseRepository).findOne(DEFAULT_ID);
	}
}