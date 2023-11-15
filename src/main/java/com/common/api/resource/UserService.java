package com.common.api.resource;

import java.util.List;

import com.common.api.response.User;


public interface UserService {

    List<User> getAllUsers() ;

	/*
	 * User getUserById(Long id) ;
	 * 
	 * User getUserByEmail(String email);
	 * 
	 * User getUserByUsername(String Username) ;
	 * 
	 * User getByIdAndUsername(Long id, String Username);
	 * 
	 * User getByUsernameAndEmail(String Username, String email);
	 * 
	 * User getByEmailAndId(String email, Long id);
	 */
	
	//void save(User user) ;

	User createUser(User user);
	 
    User updateUser(Long id, User user) ;
 
    void deleteUser(Long id) ;

	
}
