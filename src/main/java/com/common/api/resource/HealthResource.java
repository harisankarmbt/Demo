package com.common.api.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.api.constant.APIFixedConstant;
import com.common.api.response.APIGeneralSuccess;
import com.common.api.response.APIRunTimeError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Health", tags = {"Health"}) 
@RestController
@PropertySource({ "classpath:application.properties" })
public class HealthResource extends APIFixedConstant {       
   
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {  
		return new PropertySourcesPlaceholderConfigurer();
	} 
	   
	@Autowired
	private RequestService requestService;
	
	@ApiOperation(value = "View Health Status", 
				  nickname = "HealthView", 
				  notes = "View Server Health Status")  
	@ApiResponses(value = { 
			@ApiResponse(code = SC_STATUS_OK, message = SM_STATUS_OK, response = APIGeneralSuccess.class), 
            @ApiResponse(code = SC_STATUS_INTERNAL, message = SM_STATUS_INTERNAL, response = APIRunTimeError.class) 
    	})
	@RequestMapping(value = "/health", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewHealth(HttpServletRequest httpRequest) {  
		 
    	String healthMessage = "API is Up and Running";    
    	 
    	APIGeneralSuccess succPayload = new APIGeneralSuccess();       
    	succPayload.setCode("SUCCESS");     
    	succPayload.setMessage(healthMessage);    
    	 
    	return ResponseEntity.status(HttpStatus.OK.value()).body(succPayload);    
	}
	
	@ApiOperation(value = "View Remote Address", 
				  nickname = "RemoteAddress", 
				  notes = "View  Remote Address")   
	@ApiResponses(value = { 
			@ApiResponse(code = SC_STATUS_OK, message = SM_STATUS_OK, response = APIGeneralSuccess.class), 
	      @ApiResponse(code = SC_STATUS_INTERNAL, message = SM_STATUS_INTERNAL, response = APIRunTimeError.class) 
		})
	@RequestMapping(value = "/remoteAddress", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewRemoteAddress(HttpServletRequest httpRequest) {  
		 
		String remoteAddress = httpRequest.getRemoteAddr();   
		String clientIP 	 = requestService.getClientIp(httpRequest); 
		String responseMessage = "remoteAddress => " + remoteAddress + " clientIP => " + clientIP;    
		 
		APIGeneralSuccess succPayload = new APIGeneralSuccess();       
		succPayload.setCode("SUCCESS");      
		succPayload.setMessage(responseMessage);     
		 
		return ResponseEntity.status(HttpStatus.OK.value()).body(succPayload);     
	}
	 
}