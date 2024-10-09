package com.crimsonlogic.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	 private int id;
	 private String username;
	 private String password;
	 private String role;
	 private String userFirstName;
	 private String userLastName;
	 private String userEmail;
	 private String userNumber;

}
