package com.common.api.response;

public class ErrorList {

		private String fieldName;
		private String messages;
		public ErrorList() {
			
		}
		public ErrorList(String fieldName, String messages) {
			this.fieldName = fieldName;
			this.messages = messages;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getMessages() {
			return messages;
		}
		public void setMessages(String messages) {
			this.messages = messages;
		}
	    public String toString() {
	        return "ErrorList{" +
	                "fieldName= ' " + fieldName + '\'' +
	                ", messages='" + messages + '\'' +
	                '}';
	    }



}
