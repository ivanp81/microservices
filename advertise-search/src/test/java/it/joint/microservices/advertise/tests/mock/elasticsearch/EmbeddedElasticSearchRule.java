package it.joint.microservices.advertise.tests.mock.elasticsearch;

import org.junit.rules.ExternalResource;

public class EmbeddedElasticSearchRule extends ExternalResource {

	private static EmbeddedElasticsearch embeddedElasticSearch;

	@Override
	protected void before() throws Throwable {
		embeddedElasticSearch = new EmbeddedElasticsearch();
		embeddedElasticSearch.startNode();
	}

	@Override
	protected void after() {
		embeddedElasticSearch.stopNode();
	}

}