package it.joint.microservices.advertise.tests.mock.broker;

import org.junit.rules.ExternalResource;

public class EmbeddedBrokerRule extends ExternalResource {

    private EmbeddedBroker embeddedBroker;

    @Override
    protected void before() throws Throwable {
	embeddedBroker = new EmbeddedBroker();
	embeddedBroker.start();
    }

    @Override
    protected void after() {
	embeddedBroker.stop();
    }
}
