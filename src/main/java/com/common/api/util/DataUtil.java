package com.common.api.util;
 
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
 
// This utility class provides methods for converting and handling data, particularly ResultSet data.
public class DataUtil {
 
	public String resultSetToString(ResultSet resultSet, String key) {
		try {
			String result = resultSet.getString(key);
			return (result != null) ? result : "";
		} catch (SQLException e) {
			return "";
		}
	}
 
	public int resultSetToInt(ResultSet resultSet, String key) {
		try {
			int result = resultSet.getInt(key);
			return (result > 0) ? result : 0;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public long resultSetToLong(ResultSet resultSet, String key) {
		try {
			long result = resultSet.getLong(key);
			return (result > 0) ? result : 0L;
		} catch(SQLException e) {
			return 0L;
		}
	}
	
	public float resultSetToFloat(ResultSet resultSet, String key) {
		try {
			float result = resultSet.getFloat(key);
			return (result > 0) ? result : 0.0f;			
		} catch(SQLException e) {
			return 0.0f;
		}
	}
	
	public List<String> resultSetToListOfString(ResultSet resultSet,String key){
		try {
			String jsonString = resultSet.getString(key);
			if(jsonString != null) {
				ObjectMapper objectMapper=new ObjectMapper();
				return Arrays.asList(objectMapper.readValue(jsonString,String[].class));				
			}
			return Collections.emptyList();
		} catch(SQLException | IOException e) {
			return Collections.emptyList(); 
		}
	}
	
	public Date resultSetToDate(ResultSet resultSet, String key) {
		        try {
		            Date result = resultSet.getDate(key);
		            return (result != null) ? result : null;
		        } catch (SQLException e) {
		            return null;
		        }
		    }
 
	public char resultSetToChar(ResultSet resultSet, String key) {
	    	char charValue = '\u0000';
	        try {
	            String result = resultSet.getString(key);
	            if (result != null && !result.isEmpty()) {
	            	charValue =  result.charAt(0);
	            } else {
	            	charValue =  '\u0000';
	            }
	        } catch (SQLException e) {
	        	charValue =  '\u0000';
	        }
	        return charValue;
	    }
	  
	public String convertListToJson(List<?> list) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(list);
		} catch(Exception e) {
			throw new RuntimeException("Json error",e);
		}
	}
}