package com.employee.rest.exception;

public class EmployeeServiceDBException extends EmployeeServiceServerException {

  private static final long serialVersionUID = 1L;

  public EmployeeServiceDBException() {
    super();
  }

  public EmployeeServiceDBException(String message, Integer errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public EmployeeServiceDBException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public EmployeeServiceDBException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmployeeServiceDBException(String message) {
    super(message);
  }

  public EmployeeServiceDBException(Throwable cause) {
    super(cause);
  }
}
