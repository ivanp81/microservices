package it.joint.microservices.advertise.tests.mock.broker;

import org.junit.rules.ExternalResource;

public class EmbeddedBrokerRule extends ExternalResource {

	private EmbeddedBrokerManager brokerManager;

	@Override
	protected void before() throws Throwable {
		brokerManager = new EmbeddedBrokerManager();
		brokerManager.startBroker();
	}

	@Override
	protected void after() {
	}
}
