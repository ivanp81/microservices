package it.joint.microservices.advertise.acceptanceTests;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import it.joint.microservices.advertise.acceptanceTests.config.AcceptanceTestsConfiguration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

import java.net.URI;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AcceptanceTestsConfiguration.class })
public class AdvertiseSearchE2ETest {

    @Autowired
    private URI baseUri;

    private static final String QUERY = "Lorem ipsum";

    @Test
    public void testGetAdvertise() {

	when().get(baseUri.toString() + "/api/searchengine/advertises/_search?q=" + QUERY).then()
		.statusCode(equalTo(200));
    }
}