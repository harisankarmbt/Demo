package com.common.api.response;

import java.util.List;

public class ErrorResponse {


		    private String code;
		    private List<ErrorList> errorList;
		    
		    public ErrorResponse() {
		    	
		    }
		    public ErrorResponse(String code) {
		    	this.code = code;
		    }
		    public ErrorResponse(String code, List<ErrorList> errorList){
		    	this.code = code;
		    	this.errorList = errorList;
		    }
		 
		    public String getCode() {
		        return code;
		    }
		 
		    public void setCode(String code) {
		        this.code = code;
		    }
		    public List<ErrorList> getErrorList() {
		        return errorList;
		    }
		 
		    public void setErrorList(List<ErrorList> errorList) {
		        this.errorList = errorList;
		    }
		    @Override
		    public String toString() {
		        return "ErrorResponse{" +
		                "code='" + code + '\'' +
		                ", errorList=" + errorList +
		                '}';
		    }
		    
	 
	

}
