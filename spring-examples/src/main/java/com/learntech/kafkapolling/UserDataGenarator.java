//package com.learntech.kafkapolling;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.learntech.kafkapolling.model.User;
//import com.learntech.kafkapolling.producer.UserEventProducer;
//
///**
// * @author Uthiraraj Saminathan
// *
// */
//@Component
//public class UserDataGenarator implements CommandLineRunner{
//
//	private final UserEventProducer userEventProducer;
//	
//	public UserDataGenarator(UserEventProducer userEventProducer) {
//		this.userEventProducer = userEventProducer;
//	}
//
//
//	@Override
//	public void run(String... args) throws Exception {
//		userEventProducer.publishUserEvents(buildUserList());
//	}
//	
//	private List<User> buildUserList(){
//		
//		List<User> users = new ArrayList<>();
//		
//		for(int i=0; i<100; i++) {
//			User user = new User();
//			
//			user.setId(i);
//			user.setFirstName("FirstName"+i);
//			user.setLastName("LastName"+i);
//			user.setEmailId("test_"+i+"@gmail.com");
//			
//			users.add(user);
//			
//		}
//		return users;
//	}
//	
//	
//
//}
