package it.joint.microservices.advertise.acceptanceTests.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcceptanceTestsConfig {
	
	private static final String ADVERTISE_URL = System.getProperty("acceptance.advertise.url", "http://localhost:8080");
	
	@Bean
	public URI baseUri() throws URISyntaxException {
		return new URI(ADVERTISE_URL);
	}
}
