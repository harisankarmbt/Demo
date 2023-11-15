package com.common.api.resource;

import java.util.List;	

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.common.api.response.User;
import com.common.api.rowmapper.UserRowMapper;

@Service 
public class UserServiceImpl implements UserService {
	
	private final JdbcTemplate jdbcTemplate;
	

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User createUser(User user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail());
        return user;
    }
    
	/*
	 * 
	 * public User getUserById(Long id) { String sql =
	 * "SELECT * FROM users WHERE id = ?"; return jdbcTemplate.queryForObject(sql,
	 * new UserRowMapper(), id); }
	 * 
	 * 
	 * public User getUserByUsername(String username) { String sql =
	 * "SELECT * FROM users WHERE username = ?"; return
	 * jdbcTemplate.queryForObject(sql, new UserRowMapper(), username); }
	 * 
	 * 
	 * public User getUserByEmail(String email) { String sql =
	 * "SELECT * FROM users WHERE email = ?"; return
	 * jdbcTemplate.queryForObject(sql, new UserRowMapper(), email); }
	 * 
	 * public User getByIdAndUsername(Long id, String Username) { String sql =
	 * "SELECT * FROM users WHERE id = ? AND username = ?"; return
	 * jdbcTemplate.queryForObject (sql, new UserRowMapper(), id, Username); }
	 * 
	 * 
	 * public User getByUsernameAndEmail(String Username, String email) { String sql
	 * = "SELECT * FROM users WHERE Username = ? AND email = ?"; return
	 * jdbcTemplate.queryForObject(sql, new UserRowMapper(), Username, email); }
	 * 
	 * public User getByEmailAndId(String email, Long id) { String sql =
	 * "SELECT * FROM users WHERE email = ? AND id = ?"; return
	 * jdbcTemplate.queryForObject(sql, new UserRowMapper(),email, id); }
	 * 
	 * 
	 */
    
    public User updateUser(Long id, User user) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), id);
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
    }
 
    
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
 

}
