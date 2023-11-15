package com.common.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.api.response.ErrorList;
import com.common.api.response.ErrorResponse;
import com.common.api.response.User;
import com.common.api.util.FieldValidator;
import com.common.api.util.UserPayLoadValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User", tags = {"User"}) 
@RestController
@Service
@PropertySource({ "classpath:application.properties" })
public class UserResource {       

    @Autowired
    UserService userService;
    

    @ApiOperation(value = "View All Users", 
                  nickname = "AllUsersView", 
                  notes = "View All Users")  
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK", response = List.class), 
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) 
        })
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {  
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK.value()).body(users);    
    }

	/*
	 * @ApiOperation(value = "View User by ID", nickname = "UserByIdView", notes =
	 * "View User by ID")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 200, message = "OK", response = User.class),
	 * 
	 * @ApiResponse(code = 500, message = "Internal Server Error", response =
	 * ErrorResponse.class) })
	 * 
	 * @RequestMapping(value = "/userById/{id}", method = RequestMethod.GET,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<User>
	 * getUserById(HttpServletRequest httpRequest, @RequestParam Long id){ User user
	 * = userService.getUserById(id); return
	 * ResponseEntity.status(HttpStatus.OK.value()).body(user); }
	 * 
	 * @ApiOperation(value = "Get User by ID", nickname = "GetUser", notes =
	 * "Get User by ID")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
	 * 
	 * @ApiResponse(code = 500, message = "Internal Server Error", response =
	 * ErrorResponse.class) })
	 * 
	 * @RequestMapping(value = "/user/getUser", method = RequestMethod.GET, produces
	 * = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?>
	 * getUser(HttpServletRequest httpRequest, @RequestParam(value = "id",
	 * defaultValue = "0") Long userId) { List<ErrorList> errorList =
	 * FieldValidator.userValidation(userId);
	 * 
	 * if (errorList.size() <= 0) { if (userId == 0) { List<User> users =
	 * userService.getAllUsers(); return new ResponseEntity<>(users, HttpStatus.OK);
	 * } if (userId > 0) { User user = userService.getUserById(userId); return new
	 * ResponseEntity<>(user, HttpStatus.FOUND); } } return new ResponseEntity<>(new
	 * ErrorResponse("E001", errorList), HttpStatus.BAD_REQUEST); }
	 */
    
    @ApiOperation(value = "Create User", 
                  nickname = "CreateUser", 
                  notes = "Create User")   
    @ApiResponses(value = { 
            @ApiResponse(code = 201, message = "Created", response = ResponseEntity.class), 
            @ApiResponse(code = 412, message = "Precondition Failed", response = ErrorResponse.class) 
        })
    @RequestMapping(value = "/user/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) 
    {
        List<ErrorList> errorList = UserPayLoadValidator.validateUser(user);
        
        if (errorList.size() <= 0) {
        	userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } 
        return new ResponseEntity<>(new ErrorResponse("E002", errorList), HttpStatus.PRECONDITION_FAILED); 
    }

    @ApiOperation(value = "Update User", 
                  nickname = "UpdateUser", 
                  notes = "Update User")   
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK", response = User.class), 
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) 
        })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestParam Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK.value()).body(updatedUser);
    }

    @ApiOperation(value = "Delete User", 
                  nickname = "DeleteUser", 
                  notes = "Delete User")   
    @ApiResponses(value = { 
            @ApiResponse(code = 204, message = "No Content", response = ResponseEntity.class), 
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) 
        })
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
    	userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
