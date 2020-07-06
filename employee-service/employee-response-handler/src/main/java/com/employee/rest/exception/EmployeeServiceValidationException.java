package com.employee.rest.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class EmployeeServiceValidationException extends Exception {
  private static final long serialVersionUID = 1L;
  private Integer errorCode;
  private List<FieldError> fieldErrors;

  public EmployeeServiceValidationException() {
    super();
  }

  public EmployeeServiceValidationException(Integer errorCode, List<FieldError> fieldErrors) {
    super();
    setErrorCode(errorCode);
  }

  public EmployeeServiceValidationException(Integer errorCode) {
    super();
    setErrorCode(errorCode);
  }

  public EmployeeServiceValidationException(String message, Integer errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public EmployeeServiceValidationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public EmployeeServiceValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmployeeServiceValidationException(String message) {
    super(message);
  }

  public EmployeeServiceValidationException(Throwable cause) {
    super(cause);
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

public List<FieldError> getFieldErrors() {
	return fieldErrors;
}

public void setFieldErrors(List<FieldError> fieldErrors) {
	this.fieldErrors = fieldErrors;
}

}
