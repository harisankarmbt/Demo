package com.common.api.util;

import java.util.ArrayList;
import java.util.List;

import com.common.api.response.ErrorList;

public class FieldValidator {

		public static List<ErrorList> userValidation(Long userId) {
	        List<ErrorList> errorList = new ArrayList<>();

	        if (userId < 0) {
	            errorList.add(new ErrorList("Id", "Invalid User ID"));
	        }
	        if (userId == 0) {
	        	return errorList;
	        }

	        return errorList;
	    }
	

}
