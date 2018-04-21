package it.joint.microservices.advertise.tests.mock.broker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedBrokerConfig {

    @Bean
    public DummyAdvertiseBroadcastReceiver broadcastReceiver() {
	return new DummyAdvertiseBroadcastReceiver();
    }
}
