package com.common.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Department {
	
	@JsonProperty("id")
	private int departmentId = 0;
	
	@JsonProperty("department_name")
	private String departmentName = "";
	
	public Department() {
		
	}

	public Department(int departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}
	
	
}
