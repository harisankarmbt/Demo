package com.common.api.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.api.response.Department;
import com.common.api.util.DataUtil;

public class DepartmentRowMapper extends DataUtil implements RowMapper <Department> {
	
	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Department department = new Department();
		
		try {
			department.setDepartmentId(resultSetToInt(rs, "id"));
			department.setDepartmentName(resultSetToString(rs, "department_name"));
		}catch(Exception e) {
			
		}
		return department;
	}
}
