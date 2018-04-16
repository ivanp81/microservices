package it.joint.microservices.advertise.componentTests;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.util.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AdvertiseComponentTest extends ElasticSearchIntegrationTests {

	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private AdvertiseRepository addressRepository;
		
	private Advertise advertise;
	
    @Before
    public void setUp() {
    	
    	advertise = new Advertise.Builder()
								 .withId("1")
								 .withTitle(DEFAULT_TITLE)
								 .withContent(DEFAULT_CONTENT)
								 .build();

    	addressRepository.deleteAll();
    	addressRepository.save(advertise);
    }
    
	@Test
	public void givenValidAdvertiseId_whenGetAdvertise_thenReturnAdvertise() throws Exception {

		  mvc.perform(get("/api/advertises/1"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		   .andExpect(content().string(TestUtil.convertObjectToJsonString(advertise)));
	}
}