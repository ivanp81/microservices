package it.joint.microservices.advertise.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQBroadcastConfig {

    public final static String advertiseTopicExchangeName = "it.joint.microservices.advertise.exchange";

    public final static String advertiseSavedQueueName = "it.joint.microservices.advertise.saved";
    public final static String advertiseDeletedQueueName = "it.joint.microservices.advertise.deleted";

    @Bean
    public List<Declarable> topicBindings() {

	Queue advertiseSavedQueue = new Queue(advertiseSavedQueueName);
	Queue advertiseDeletedQueue = new Queue(advertiseDeletedQueueName);

	TopicExchange topicExchange = new TopicExchange(advertiseTopicExchangeName);

	return Arrays.asList(advertiseSavedQueue, advertiseDeletedQueue, topicExchange,
		BindingBuilder.bind(advertiseSavedQueue).to(topicExchange).with("#.saved"),
		BindingBuilder.bind(advertiseDeletedQueue).to(topicExchange).with("#.deleted"));
    }

    // @Bean
    // public SimpleRabbitListenerContainerFactory container(ConnectionFactory
    // connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer configurer)
    // {
    // SimpleRabbitListenerContainerFactory factory = new
    // SimpleRabbitListenerContainerFactory();
    // configurer.configure(factory, connectionFactory);
    // return factory;
    // }
}
