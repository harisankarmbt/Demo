package com.common.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; // Added

import com.common.api.constant.APIFixedConstant;
import com.common.api.response.Department;
import com.common.api.util.ErrorValidation;


@RestController // Added - Defines this class as a REST Controller
public class DepartmentResource extends APIFixedConstant  {
	
	@Value("${DELETE_SUCCESS}")
	public String deleteSuccess = "";
	
	@Value("${DELETE_ALL_SUCCESS}")
	public String deleteAllRecordsSuccess = "";

	@Value("${INSERT_EXCEPTION_ERROR}")
	public String insertExceptionError = "";

	@Value("${UPDATE_EXCEPTION_ERROR}")
	public String updateExceptionError = "";

	@Value("${DELETE_EXCEPTION_ERROR}")
	public String deleteExceptionError = "";

	@Value("${RECORD_NOT_FOUND}")
	public String recordNotFound = "";
    
    @Autowired
    DepartmentService departmentDao;
    
    @Autowired
    private ErrorValidation errorValidation;
    
    @RequestMapping(value = "/department", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDepartment(
            @RequestParam(name = "id", required = false, defaultValue = "0") Integer id,
            @RequestParam(name = "departmentName", required = false, defaultValue = "") String departmentName) {
        ResponseEntity<Object> response = ResponseEntity.ok("");
        
        ErrorValidation errorResponse = errorValidation.validateInput(Optional.of(id), Optional.empty());
        if(!errorResponse.getErrors().isEmpty()) {
        	response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }else {
        	try {
                List<Department> departments = departmentDao.getDepartment(id, departmentName);
                System.out.println(departments);
                if (departments.isEmpty()) {
                	errorResponse.addError(RECORD_NOT_FOUND);
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
                } else {
                    response = ResponseEntity.status(HttpStatus.OK).body(departments);
                }
            } catch (DataAccessException e) {
            } 
        }
         
        return response;
    }
    
    
    @RequestMapping(value = "/department", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addDepartment(@RequestBody Department department) {
		ResponseEntity<Object> response = ResponseEntity.ok("");
		ErrorValidation errorResponse = errorValidation.validateInput(Optional.empty(), Optional.of(department));
        if(!errorResponse.getErrors().isEmpty()) {
        	response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }else {
        	try {
				Department addedDepartment = departmentDao.addDepartment(department);
				int addedId = addedDepartment.getDepartmentId();
				if (addedId > 0) {
					response = ResponseEntity.status(HttpStatus.CREATED).body(addedDepartment);
				} else {
					response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(insertExceptionError);
				}
			} catch (DataAccessException e) {	}
        }
			
		return response;
	}
    
    
    @RequestMapping(value = "/department", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateDepartment(
            @RequestParam(name = "id") int id,
            @RequestBody Department department) {

    	ResponseEntity<Object> response = ResponseEntity.ok("");
    	
    	ErrorValidation errorResponse = errorValidation.validateInput(Optional.of(id), Optional.of(department));
        if(!errorResponse.getErrors().isEmpty()) {
        	response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }else {
        	try {
                department.setDepartmentId(id); // Set the ID from the request parameter
                int rowsAffected = departmentDao.updateDepartment(department);

                if (rowsAffected > 0) {
                    response = ResponseEntity.status(HttpStatus.ACCEPTED).body(department);
                } else {
                	errorResponse.addError(RECORD_NOT_FOUND);
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
                }
            } catch (DataAccessException e) {
            }
        }
        return response;
    }
    
    
    @RequestMapping(value = "/department", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteDepartment(
            @RequestParam(name = "id",required = false,defaultValue = "0") int id){
    	ResponseEntity<Object> response = ResponseEntity.ok("");
    	 ErrorValidation errorResponse = errorValidation.validateInput(Optional.of(id), Optional.empty());
         if(!errorResponse.getErrors().isEmpty()) {
         	response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
         }else {
	    	try {
	    		int rowsAffected = departmentDao.deleteDepartment(id);
	    		String successMessage = (id > 0) ? deleteSuccess : deleteAllRecordsSuccess;
	    		if(rowsAffected > 0) {
	                response = ResponseEntity.status(HttpStatus.ACCEPTED).body(successMessage);
	    		}else {
	    			errorResponse.addError(RECORD_NOT_FOUND);
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    		}
	    	}catch(DataAccessException e) {
	    		e.printStackTrace();
	    		
	    	}
         }
    	return response;
    }
    
    

}
