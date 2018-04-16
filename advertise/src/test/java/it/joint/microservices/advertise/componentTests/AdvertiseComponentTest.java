package it.joint.microservices.advertise.componentTests;

import it.joint.microservices.advertise.AdvertiseApplication;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.tests.mock.broker.DummyAdvertiseMessageConsumer;
import it.joint.microservices.advertise.tests.mock.broker.EmbeddedBrokerConfig;
import it.joint.microservices.advertise.tests.mock.broker.EmbeddedBrokerRule;
import it.joint.microservices.advertise.util.JSONUtil;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes = {AdvertiseApplication.class, EmbeddedBrokerConfig.class})
public class AdvertiseComponentTest {

	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

    @ClassRule
    public static EmbeddedBrokerRule embeddedBrokerRule = new EmbeddedBrokerRule();

	@Autowired
    private MockMvc mvc;
			
	@Autowired
    private DummyAdvertiseMessageConsumer advertiseMessageConsumer;
    
	@Test
	public void testCreateAdvertise() throws Exception {

		advertiseMessageConsumer.initCounter();
		Advertise advertise = new Advertise.Builder().withTitle(DEFAULT_TITLE).withContent(DEFAULT_CONTENT).build();


		mvc.perform(post("/api/advertises").content(JSONUtil.serializeToJson((advertise)))
				.contentType("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(notNullValue())))
				.andExpect(jsonPath("$.title", is(DEFAULT_TITLE)))
				.andExpect(jsonPath("$.content", is(DEFAULT_CONTENT)));
		
		Thread.sleep(3000);
    	assertThat(advertiseMessageConsumer.getCounter(), equalTo(1));
	}
}