package com.common.api.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.api.response.User;

public class UserRowMapper implements RowMapper<User>{

		@Override 
		public User mapRow(ResultSet rs, int rowNum) throws SQLException{
			User user=new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("Username"));
			user.setEmail(rs.getString("email"));
			return user;
		}
}
