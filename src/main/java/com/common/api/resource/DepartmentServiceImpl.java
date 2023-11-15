package com.common.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.common.api.constant.APIFixedConstant;
import com.common.api.response.Department;
import com.common.api.rowmapper.DepartmentRowMapper;


@Repository
public class DepartmentServiceImpl extends APIFixedConstant implements DepartmentService {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Department> getDepartment(Integer id, String departmentName) {
		List<Department> response = new ArrayList<>();
		try {
		String sql = "SELECT * FROM " + TABLE_DEPARTMENT  + 
				" WHERE " +
					"( (:id > 0 AND id = :id ) OR (:id <=  0) ) AND " +
					"( (length(:departmentName) > 0 AND department_name = :departmentName ) OR (length(:departmentName) <= 0) )";
		
		MapSqlParameterSource mapper = new MapSqlParameterSource();
		
		mapper.addValue("id", id);
		mapper.addValue("departmentName", departmentName);
		response = namedParameterJdbcTemplate.query(sql, mapper, new DepartmentRowMapper());
		}catch(DataAccessException e) {
			
		} 
		return response;
	}

	@Override
	public Department addDepartment(Department department) {
		try {
			String sql = "INSERT INTO " + TABLE_DEPARTMENT  + " (department_name) VALUES (:department_name)";
		    MapSqlParameterSource mapper = new MapSqlParameterSource();
		    mapper.addValue("department_name", department.getDepartmentName());

		    KeyHolder keyHolder =  new GeneratedKeyHolder();
		    int rowsAffected = namedParameterJdbcTemplate.update(sql, mapper, keyHolder);
		    Number generatedkey = keyHolder.getKey();

		    if(rowsAffected > 0 && generatedkey != null) {
		    	department.setDepartmentId(generatedkey.intValue());
		    	return department;
		    }else {

		    	return new Department();
		    }
		}catch(DataAccessException e) {
		}
		return department;
	}
	
	@Override
	public int updateDepartment(Department department) {
		int response = 0;
		try {
			String sql = "UPDATE " + TABLE_DEPARTMENT + " SET"
					+ " department_name = :departmentName "
				+ "WHERE id = :id";
			MapSqlParameterSource mapper = new MapSqlParameterSource();
			mapper.addValue("id", department.getDepartmentId());
			mapper.addValue("departmentName", department.getDepartmentName());

			response = namedParameterJdbcTemplate.update(sql, mapper);
		}catch(DataAccessException e) {
		}
		return response;
	}

	@Override
	public int deleteDepartment(int id) {
	    int response = 0;
	    try {
	    	String sql = "DELETE FROM "+ TABLE_DEPARTMENT +" WHERE "
					+ "((:id > 0 AND id = :id) OR (:id <= 0))";
	        MapSqlParameterSource mapper = new MapSqlParameterSource();
	        mapper.addValue("id", id);
	        response = namedParameterJdbcTemplate.update(sql, mapper);
	    } catch (DataAccessException e) {
	        // Handle exceptions here
	    }
	    return response;
	}



}
