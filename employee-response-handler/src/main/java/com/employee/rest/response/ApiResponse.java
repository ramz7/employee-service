package com.employee.rest.response;

import com.employee.rest.enums.RestApiResponseStatus;

/**
 * 
 * This base class contains all the common attributes that any REST web service
 * response should contain. All the REST web service Response classes should
 * extend this!
 *
 */

public class ApiResponse {
	public static final ApiResponse OK = new ApiResponse(RestApiResponseStatus.OK);

	public ApiResponse(RestApiResponseStatus restApiResponseStatus) {
		this.status = restApiResponseStatus.getStatus();
		this.statusCode = restApiResponseStatus.getCode();
	}


	private String status;

	private Integer statusCode;



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	

}
