package it.joint.microservices.advertise.integrationTests.api;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.MockitoAnnotations.initMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import it.joint.microservices.advertise.api.AdvertiseController;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;
import it.joint.microservices.advertise.util.JSONUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(AdvertiseController.class)
@ActiveProfiles("test")
public class AdvertiseControllerTest {

    private static final String DEFAULT_ID = "11111111";
    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

    private static final String UPDATED_TITLE = "BBBBBBBBBBBB";
    private static final String UPDATED_CONTENT = "BBBBBBBBBBBB";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdvertiseRepository addressRepository;

    @Before
    public void setUp() {
	initMocks(this);
    }

    @Test
    public void testCreateAdvertise() throws Exception {

	Advertise advertise = new Advertise.Builder().withTitle(DEFAULT_TITLE).withContent(DEFAULT_CONTENT).build();

	doAnswer(new Answer<Advertise>() {
	    public Advertise answer(InvocationOnMock invocation) throws Throwable {
		Advertise advertise = invocation.getArgumentAt(0, Advertise.class);
		advertise.setId(DEFAULT_ID);
		return advertise;
	    }
	}).when(addressRepository).save(any(Advertise.class));

	mvc.perform(post("/api/advertises").content(JSONUtil.serializeToJson((advertise)))
		.contentType("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(DEFAULT_ID))).andExpect(jsonPath("$.title", is(DEFAULT_TITLE)))
		.andExpect(jsonPath("$.content", is(DEFAULT_CONTENT)));
    }

    @Test
    public void testUpdateAdvertise() throws Exception {

	Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(UPDATED_TITLE)
		.withContent(UPDATED_CONTENT).build();

	doReturn(advertise).when(addressRepository).save(advertise);

	mvc.perform(put("/api/advertises").content(JSONUtil.serializeToJson((advertise)))
		.contentType("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(DEFAULT_ID))).andExpect(jsonPath("$.title", is(UPDATED_TITLE)))
		.andExpect(jsonPath("$.content", is(UPDATED_CONTENT)));
    }

    @Test
    public void testDeleteAdvertise() throws Exception {

	mvc.perform(delete("/api/advertises/" + DEFAULT_ID).contentType("application/json;charset=UTF-8"))
		.andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetAdvertise() throws Exception {

	Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE)
		.withContent(DEFAULT_CONTENT).build();

	doReturn(advertise).when(addressRepository).findOne(DEFAULT_ID);

	mvc.perform(get("/api/advertises/" + DEFAULT_ID).contentType("application/json;charset=UTF-8")).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(DEFAULT_ID)))
		.andExpect(jsonPath("$.title", is(DEFAULT_TITLE)))
		.andExpect(jsonPath("$.content", is(DEFAULT_CONTENT)));
    }

    @Test
    public void testGetAdvertises() throws Exception {

	Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE)
		.withContent(DEFAULT_CONTENT).build();
	List<Advertise> advertises = Arrays.asList(new Advertise[] { advertise });

	doReturn(advertises).when(addressRepository).findAll();

	mvc.perform(get("/api/advertises").contentType("application/json;charset=UTF-8")).andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(DEFAULT_ID)))
		.andExpect(jsonPath("$[0].title", is(DEFAULT_TITLE)))
		.andExpect(jsonPath("$[0].content", is(DEFAULT_CONTENT)));
    }

}