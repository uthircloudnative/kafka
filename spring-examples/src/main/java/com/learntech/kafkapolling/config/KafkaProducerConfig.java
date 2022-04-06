package com.learntech.kafkapolling.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Uthiraraj Saminathan
 *
 */
@Configuration
public class KafkaProducerConfig {
	
	@Value(value = "${user.topic.name}")
	private String topicName;
	
	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(topicName)
				           .partitions(1)
				           .build();
		
		
	}
	
}
