package com.common.api.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ValidateUtil {

	private List<String> errors = new ArrayList<>();

	public void addError(String error) {
		errors.add(error);
	}

	public List<String> getErrors() {
		return errors;
	}

	public String validateStringField(String value, String fieldName, Optional<String> regex) {
		try {
			String val = value.toString();
			if (val == null) {
				addError(fieldName + " is required");
			} else {
				regex.ifPresent(regexValue -> {
					if (!Pattern.matches(regexValue, val)) {
						addError(fieldName + " is invalid");
					}
				});
			}
		} catch (Exception e) {
		}
		
		return "";
		
	}
	
	public int validateIntegerField(Integer value, String fieldName, Optional<String> regex) {
		if (value < 0) {
			addError( fieldName + " value should not be zero or less than zero");
		} else {
			try {
				String val = value.toString();
				regex.ifPresent(regexValue -> {
					if (!Pattern.matches(regexValue, val)) {
						addError(fieldName + " is invalid");
					}
				});

			} catch (Exception e) {
			}

		}
		return 0;

	}
	
	public long validateLongField(Long value, String fieldName, Optional<String> regex) {
		try {
			String val = value.toString();
			if (val == null) {
				addError(fieldName + " is required");
			} else {
				regex.ifPresent(regexValue -> {
					if (!Pattern.matches(regexValue, val)) {
						addError(fieldName + " is invalid");
					}
				});
			}
		} catch (Exception e) {
		}

		return 0;

	}
	
	public float validateFloatField(Float value, String fieldName, Optional<String> regex) {
		try {
			String val = value.toString();
			if (val == null) {
				addError(fieldName + " is required");
			} else {
				regex.ifPresent(regexValue -> {
					if (!Pattern.matches(regexValue, val)) {
						addError(fieldName + " is invalid");
					}
				});
			}
		} catch (Exception e) {
		}

		return 0.0f;

	}
	
	public char validateCharField(Character value, String fieldName, Optional<String> regex) {
		try {
			String val = value.toString();
			if (val == null) {
				addError(fieldName + " is required");
			} else {
				regex.ifPresent(regexValue -> {
					if (!Pattern.matches(regexValue, val)) {
						addError(fieldName + " is invalid give single Character");
					}
				});
			}
		} catch (Exception e) {
		}
		return value;  
	}
	
	public Date validateDateField(Date value, String fieldName, String dateFormat, Optional<String> regex) {
		try {
			if (value == null) {
				addError(fieldName + " is required");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				sdf.parse(value.toString());
			}
		} catch (Exception e) {
		}

		return new Date(0);
	}

	public String[] validateStringArray(List<String> list, String fieldName, String regex) {
	    List<String> errors = new ArrayList<>();
	    try {
	        if (list == null) {
	            errors.add(fieldName + " is required");
	        } else {
	            for (int i = 0; i < list.size(); i++) {
	                String value = list.get(i);
	                if (value == null || !Pattern.matches(regex, value)) {
	                    errors.add(fieldName + " [" + i + "] " + " is invalid");
	                }
	            }
	        }
	    } catch (Exception e) {
	    }
	    
	    return errors.toArray(new String[0]);
	}
	
}