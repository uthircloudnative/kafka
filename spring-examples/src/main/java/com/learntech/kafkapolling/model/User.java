package com.learntech.kafkapolling.model;

import lombok.Data;

/**
 * @author Uthiraraj Saminathan
 *
 */
@Data
public class User {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String emailId;

}
