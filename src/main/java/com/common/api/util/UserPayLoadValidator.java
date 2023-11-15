package com.common.api.util;

import java.util.ArrayList;
import java.util.List;

import com.common.api.response.ErrorList;
import com.common.api.response.User;

public class UserPayLoadValidator {
	public static List<ErrorList> validateUser(User user) {
		List<ErrorList> errorList = new ArrayList<>();

		String username = user.getUsername();
		String email    = user.getEmail(); 
		Long id = user.getId();
		  if (id == null || id <= 0) {
	            errorList.add(new ErrorList("id", "Invalid id"));
	        }
		  
		if (username == null || username.isEmpty() || username.length() <= 0)  
			errorList.add(new ErrorList("Username", "Invalid Username")); 
				
		if (email != null  && email.endsWith("@example.com") || email.length() <= 0)  
			errorList.add(new ErrorList("Email", "Invalid email"));
		
		return errorList;
		
	}


}
