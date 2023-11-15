package com.common.api.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.common.api.constant.APIFixedConstant;
import com.common.api.resource.DepartmentService;
import com.common.api.response.Department;

@Component
public class ErrorValidation extends APIFixedConstant {
	   
	   @Autowired
	   DepartmentService departmentService;
	   
	   @Autowired
	   NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	   
	
		
	   private final List<String> errors = new ArrayList<>();
	   
	    public void addError(String error) {
	            errors.add(error);
	    }
	    
	    public void addErrors(List<String> error) {
            errors.addAll(error);
    }

	    public List<String> getErrors() {
	        return errors;
	    }
    
	  
	    
    public ErrorValidation validateInput(Optional<Integer> id, Optional<Object> input) {
 	
        ErrorValidation errorResponse = new ErrorValidation();      
        ValidateUtil validateUtil = new ValidateUtil();
        
        if (id.isPresent()) {
            Integer idValue = id.get();
            if (idValue < 0) {
                errorResponse.addError(INVALID_ID_FORMAT);
            } 
        }
	    
   
        if(input.isPresent()){
        	input.ifPresent(inputValue -> {           
            if(inputValue instanceof Department) {
    			Department department = (Department) inputValue;
    			validateUtil.validateStringField(department.getDepartmentName(), "department_name", Optional.of(NAME_REGEX));
    			errorResponse.addErrors(validateUtil.getErrors());
    		}
                       
        });
    
        }
	  
        return errorResponse;
    }

 
}
