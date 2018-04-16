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
@ContextConfiguration(classes = {AcceptanceTestsConfiguration.class })
public class AdvertiseIndexE2ETest {

	@Autowired
	private URI baseUri;
		
	@Test
	public void givenId_whenGetAdvertises_thenStatusCodeIs200() {

		when().get(baseUri.toString() + "/api/advertises/1")
			.then()
			.statusCode(equalTo(200));
	}
}