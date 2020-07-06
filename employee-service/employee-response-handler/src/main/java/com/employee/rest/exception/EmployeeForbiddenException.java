package com.employee.rest.exception;

public class EmployeeForbiddenException extends EmployeeServiceServerException {

  private static final long serialVersionUID = 1L;

  public EmployeeForbiddenException() {
    super();
  }

  public EmployeeForbiddenException(Integer errorCode) {
    super();
    setErrorCode(errorCode);
  }

  public EmployeeForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

}
