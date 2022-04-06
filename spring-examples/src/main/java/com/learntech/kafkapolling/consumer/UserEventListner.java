package com.learntech.kafkapolling.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.learntech.kafkapolling.model.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
@Component
public class UserEventListner {
	
	private static final Logger logger = LoggerFactory.getLogger(UserEventListner.class);
	
	@KafkaListener(id = "userListner", topics = {"user_topic"})
	public void listenUserEvent(User user) {
		logger.info(user.toString());
	}

}
