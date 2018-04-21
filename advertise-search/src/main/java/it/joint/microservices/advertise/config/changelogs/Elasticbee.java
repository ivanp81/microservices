package it.joint.microservices.advertise.config.changelogs;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;

public class Elasticbee implements InitializingBean {

    @Autowired
    private AdvertiseRepository advertiseRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
	execute();
    }

    public void execute() {

	advertiseRepository.deleteAll();

	Advertise advertise = new Advertise();
	advertise.setId("1");
	advertise.setTitle("Java back-end developer");
	advertise.setContent("Lorem ipsum");

	advertiseRepository.save(advertise);
    }

}