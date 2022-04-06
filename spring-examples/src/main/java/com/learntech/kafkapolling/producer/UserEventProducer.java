package com.learntech.kafkapolling.producer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.learntech.kafkapolling.model.Response;
import com.learntech.kafkapolling.model.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
@Component
public class UserEventProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(UserEventProducer.class);
	
	@Value(value = "${user.topic.name}")
	private String topicName;
	
	private final KafkaTemplate<String, User> kafkaTemplate;
	

	public UserEventProducer(KafkaTemplate<String, User> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public Response publishUserEvents(List<User> users) {
		
		Response resp  = new Response();;
		for(User user:users) {
			
			ListenableFuture<SendResult<String, User>> listFuture = kafkaTemplate.send(topicName, user);
			
			listFuture.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {

				@Override
				public void onSuccess(SendResult<String, User> result) {
					logger.info("Message send Successfully");
					resp.setStatus("Message send Successfully");
				}

				@Override
				public void onFailure(Throwable ex) {
					logger.info("Unable to send message to Kafka topic");
					resp.setStatus("Unable to send message to Kafka topic");
				}
			  }
			);
		}
		return resp;
	}
	
}
