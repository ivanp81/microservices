package it.joint.microservices.advertise.componentTests.api;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.tests.mock.elasticsearch.EmbeddedElasticSearchRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AdvertiseControllerTest {

	private static final String DEFAULT_ID = "11111111";
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
	private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	private static final String QUERY = "AAAAAAAAAA";

	@ClassRule
	public static EmbeddedElasticSearchRule embeddedElasticSearchRule = new EmbeddedElasticSearchRule();

	// exclude rabbit from the test
	@MockBean
	private RabbitAdmin rabbitAdmin;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AdvertiseRepository addressRepository;

	private Advertise advertise;

	@Before
	public void setUp() {

		advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE).withContent(DEFAULT_CONTENT)
				.build();

		addressRepository.deleteAll();
		addressRepository.save(advertise);
	}

	@Test
	public void testGetAdvertise() throws Exception {

		mvc.perform(get("/api/searchengine/advertises/_search?q=" + QUERY)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(DEFAULT_ID))).andExpect(jsonPath("$[0].title", is(DEFAULT_TITLE)))
				.andExpect(jsonPath("$[0].content", is(DEFAULT_CONTENT)));
	}
}