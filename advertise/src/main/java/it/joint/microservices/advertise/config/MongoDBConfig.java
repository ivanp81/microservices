package it.joint.microservices.advertise.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableMongoRepositories("it.joint.microservices.advertise.domain.repository")
public class MongoDBConfig {

	private final Logger log = LoggerFactory.getLogger(MongoDBConfig.class);
	
	@Bean
	@Profile("testing")
    public Mongobee mongobee(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        log.debug("Configuring Mongobee");
        Mongobee mongobee = new Mongobee(mongoClient);
        mongobee.setDbName(mongoProperties.getDatabase());
        mongobee.setMongoTemplate(mongoTemplate);
        // package to scan for migrations
        mongobee.setChangeLogsScanPackage("it.joint.microservices.advertise.config.changelogs");
        mongobee.setEnabled(true);
        return mongobee;
    }
}
