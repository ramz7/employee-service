package com.employee.rest.exception;

public class EmployeeServiceServerException extends Exception {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public EmployeeServiceServerException() {
		super();
	}

	public EmployeeServiceServerException(String message, Integer errorCode) {
		super(message);
		setErrorCode(errorCode);
	}

	public EmployeeServiceServerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmployeeServiceServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeServiceServerException(String message) {
		super(message);
	}

	public EmployeeServiceServerException(Throwable cause) {
		super(cause);
	}

}
