package it.joint.microservices.advertise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import it.joint.microservices.advertise.config.changelogs.Elasticbee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableElasticsearchRepositories("it.joint.microservices.advertise.domain.repository")
public class ElasticSearchConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    @Profile("testing")
    public Elasticbee elasticbee() {
	log.info("Running Index initialization with Elasticbee");
	Elasticbee elasticbee = new Elasticbee();
	return elasticbee;
    }
}
