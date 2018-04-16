package it.joint.microservices.advertise.integrationTests.api;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import static org.mockito.MockitoAnnotations.initMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import it.joint.microservices.advertise.api.AdvertiseSearchController;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.util.TestUtil;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdvertiseSearchController.class)
@ActiveProfiles("test")
public class AdveriseControllerTest {
	
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	@Autowired
    private MockMvc mvc;

	@MockBean
    private AdvertiseRepository addressRepository;
    
    private Advertise advertise;
    
    @Before
    public void setUp() {
        initMocks(this);
        advertise = new Advertise.Builder()
        						 .withId("1")
        						 .withTitle(DEFAULT_TITLE)
        						 .withContent(DEFAULT_CONTENT)
        						 .build();
    }
        
    @Test
    public void givenValidAdvertiseId_whenGetAdvertise_thenReturnAdvertise() throws Exception {

    	doReturn(advertise).when(addressRepository).findOne("1");

        mvc.perform(get("/api/advertises/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(TestUtil.convertObjectToJsonString(advertise)));
        
        verify(addressRepository).findOne("1");
    }    
}