package it.joint.microservices.advertise.componentTests;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import it.joint.microservices.advertise.componentTests.config.EmbeddedElasticsearchServer;

public abstract class ElasticSearchIntegrationTests {
	
	private static EmbeddedElasticsearchServer embeddedElasticSearchServer;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    	
    	embeddedElasticSearchServer = new EmbeddedElasticsearchServer();
    	embeddedElasticSearchServer.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    	embeddedElasticSearchServer.shutdown();
    }
}