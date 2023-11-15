package com.common.api.resource;

import java.util.List;

import com.common.api.response.Department;

public interface DepartmentService {
	
	public List<Department> getDepartment(Integer id, String departmentName);
	
	public Department addDepartment(Department department);
	
	public int updateDepartment(Department department);
	
	public int deleteDepartment(int id);
	
	
}
