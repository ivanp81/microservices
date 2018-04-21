package it.joint.microservices.advertise.tests.mock.elasticsearch;

import org.junit.rules.ExternalResource;

public class EmbeddedElasticSearchRule extends ExternalResource {

    private static EmbeddedElasticSearch embeddedElasticSearch;

    @Override
    protected void before() throws Throwable {
	embeddedElasticSearch = new EmbeddedElasticSearch();
	embeddedElasticSearch.startNode();
    }

    @Override
    protected void after() {
	embeddedElasticSearch.stopNode();
    }

}