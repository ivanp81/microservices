package it.joint.microservices.advertise.unitTests.api;

import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;

import it.joint.microservices.advertise.api.AdvertiseController;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdvertiseControllerTest {

	private static final String DEFAULT_ID = "11111111";
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
	private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	private AdvertiseController advertiseController;

	@Mock
	private AdvertiseRepository advertiseRepository;

	@Before
	public void setUp() {

		initMocks(this);
		advertiseController = new AdvertiseController(advertiseRepository);
	}

	@Test
	public void testCreateAdvertise() {

		Advertise advertise = new Advertise.Builder().withTitle(DEFAULT_TITLE).withContent(DEFAULT_CONTENT).build();

		advertiseController.createAdvertise(advertise);

		verify(advertiseRepository).save(advertise);
	}

	@Test
	public void testUpdateAdvertise() {

		Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE)
				.withContent(DEFAULT_CONTENT).build();

		advertiseController.updateAdvertise(advertise);

		verify(advertiseRepository).save(advertise);
	}

	@Test
	public void testDeleteAdvertise() {

		advertiseController.deleteAdvertise(DEFAULT_ID);
		verify(advertiseRepository).delete(DEFAULT_ID);
	}

	@Test
	public void testGetAdvertise() {

		advertiseController.getAdvertise(DEFAULT_ID);
		verify(advertiseRepository).findOne(DEFAULT_ID);
	}
}