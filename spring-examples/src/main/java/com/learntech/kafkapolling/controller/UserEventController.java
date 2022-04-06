package com.learntech.kafkapolling.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learntech.kafkapolling.model.Response;
import com.learntech.kafkapolling.model.User;
import com.learntech.kafkapolling.producer.UserEventProducer;

/**
 * @author Uthiraraj Saminathan
 *
 */
@RestController
public class UserEventController {
	
	private final UserEventProducer userEventProducer;
	
	public UserEventController(UserEventProducer userEventProducer) {
		this.userEventProducer = userEventProducer;
	}

	@GetMapping(path = "/generateuserevent")
	public Response genarateUserEvent() {
		return userEventProducer.publishUserEvents(buildUserList());
	}

	
	private List<User> buildUserList(){
			
			List<User> users = new ArrayList<>();
			
			for(int i=0; i<100; i++) {
				User user = new User();
				
				user.setId(i);
				user.setFirstName("FirstName"+i);
				user.setLastName("LastName"+i);
				user.setEmailId("test_"+i+"@gmail.com");
				
				users.add(user);
				
			}
			return users;
		}
}
