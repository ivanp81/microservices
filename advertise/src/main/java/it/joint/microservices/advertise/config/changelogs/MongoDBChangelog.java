package it.joint.microservices.advertise.config.changelogs;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import it.joint.microservices.advertise.domain.model.Advertise;

@ChangeLog
public class MongoDBChangelog {

	@ChangeSet(order = "001", id = "dummyInitialData", author = "testAuthor")
	public void dummyInitialData(MongoTemplate mongoTemplate) {

		Advertise advertise = new Advertise();
		advertise.setId("1");
		advertise.setTitle("Java back-end developer");
		advertise.setContent("Lorem ipsum");

		mongoTemplate.save(advertise);
	}
}
