package it.joint.microservices.advertise.unitTests.domain.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import it.joint.microservices.advertise.domain.service.AdvertiseService;
import it.joint.microservices.advertise.domain.service.AdvertiseServiceImpl;

public class AdvertiseServiceTest {

    private static final String QUERY = "AAAAAAAAAA";

    private AdvertiseService advertiseService;

    @Mock
    ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void setUp() {
	initMocks(this);
	advertiseService = new AdvertiseServiceImpl(elasticsearchTemplate);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAdvertise() {

	advertiseService.searchAdvertises(QUERY);
	verify(elasticsearchTemplate).queryForList(isA(SearchQuery.class), isA(Class.class));
    }
}