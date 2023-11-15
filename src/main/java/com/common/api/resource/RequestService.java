package com.common.api.resource;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {

	String getClientIp(HttpServletRequest request);
	
}
