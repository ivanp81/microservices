package it.joint.microservices.advertise.unitTests.domain.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.List;

import it.joint.microservices.advertise.api.AdvertiseController;
import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.service.AdvertiseService;
import it.joint.microservices.advertise.domain.service.AdvertiseServiceImpl;


import static org.mockito.BDDMockito.given;

public class AdvertiseServiceTest {

	private static final String QUERY = "AAAAAAAAAA";
	
	private AdvertiseService advertiseService;

	@Mock
	ElasticsearchTemplate elasticsearchTemplate;
	
	@Mock
	List<Advertise> advertises;
	
	@Before
	public void setUp() {

		initMocks(this);
		advertiseService = new AdvertiseServiceImpl(elasticsearchTemplate);
	}

	@Test
	public void testGetAdvertise() {

       //given(elasticsearchTemplate.queryForList(isA(SearchQuery.class), isA(Class.class))).willReturn(advertises);

		advertiseService.searchAdvertises(QUERY);
		
		verify(elasticsearchTemplate).queryForList(isA(SearchQuery.class), isA(Class.class));
	}
}