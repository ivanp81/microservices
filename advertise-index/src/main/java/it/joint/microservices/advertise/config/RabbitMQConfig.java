package it.joint.microservices.advertise.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public final static String advertiseSavedQueueName = "it.joint.microservices.advertise.saved";
    public final static String advertiseDeletedQueueName = "it.joint.microservices.advertise.deleted";

	@Bean
    public Queue advertiseSavedQueue() {
        return new Queue(advertiseSavedQueueName);
    }
	
	@Bean
    public Queue advertiseDeletedQueue() {
        return new Queue(advertiseDeletedQueueName);
    }
}
