package it.joint.microservices.advertise.integrationTests.domain.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.service.AdvertiseService;
import it.joint.microservices.advertise.domain.service.AdvertiseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = RabbitAutoConfiguration.class)
public class AdvertiseServiceTest {

	private static final String DEFAULT_ID = "11111111";
	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
	private static final String DEFAULT_CONTENT = "AAAAAAAAAA";

	private static final String QUERY = "AAAAAAAAAA";

	// exclude rabbit from the test
	@MockBean
	private RabbitAdmin rabbitAdmin;

	@Autowired
	private AdvertiseService advertiseService;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Before
	public void setUp() {

		advertiseService = new AdvertiseServiceImpl(elasticsearchTemplate);

		elasticsearchTemplate.deleteIndex(Advertise.class);
		elasticsearchTemplate.createIndex(Advertise.class);
		elasticsearchTemplate.putMapping(Advertise.class);
		elasticsearchTemplate.refresh(Advertise.class);

		IndexQuery advertise = buildIndex();
		elasticsearchTemplate.index(advertise);
		elasticsearchTemplate.refresh(Advertise.class);
	}

	private IndexQuery buildIndex() {

		Advertise advertise = new Advertise.Builder().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE)
				.withContent(DEFAULT_CONTENT).build();

		IndexQuery indexQuery = new IndexQueryBuilder().withId(advertise.getId()).withObject(advertise).build();

		return indexQuery;
	}

	@Test
	public void testSearchAdvertise() {

		List<Advertise> advertises = advertiseService.searchAdvertises(QUERY);

		assertThat(advertises, hasSize(1));
	}
}