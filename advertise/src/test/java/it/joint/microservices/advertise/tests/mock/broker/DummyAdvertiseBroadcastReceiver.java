package it.joint.microservices.advertise.tests.mock.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import it.joint.microservices.advertise.config.RabbitMQBroadcastConfig;

public class DummyAdvertiseBroadcastReceiver {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private Integer counter = 0;

	@RabbitListener(queues = RabbitMQBroadcastConfig.advertiseSavedQueueName)
    public void onAdvertiseCreatedMessage(String advertiseSaved) {
		log.info("onAdvertiseCreatedMessage: Received <{}>" + advertiseSaved);
		counter++;
    }
	
	@RabbitListener(queues = RabbitMQBroadcastConfig.advertiseDeletedQueueName)
    public void onAdvertiseDeletedMessage(String advertiseDeleted) {
		log.info("onAdvertiseDeletedMessage: Received <{}>" + advertiseDeleted);
		counter++;
	}
	
	public Integer getCounter() {
        return counter;
    }

    public void initCounter() {
        this.counter = 0;
    }
}