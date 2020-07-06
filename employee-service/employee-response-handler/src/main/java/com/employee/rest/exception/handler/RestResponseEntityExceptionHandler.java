package com.employee.rest.exception.handler;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.employee.rest.enums.RestApiResponseStatus;
import com.employee.rest.exception.EmployeeForbiddenException;
import com.employee.rest.exception.EmployeeServiceDBException;
import com.employee.rest.exception.EmployeeServiceServerException;
import com.employee.rest.response.ApiResponse;
import com.employee.rest.response.ValidationFailureResponse;
import com.employee.rest.validation.ValidationFailure;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger exceptionlogger =
      Logger.getLogger(RestResponseEntityExceptionHandler.class);

  @ExceptionHandler
  protected ResponseEntity<ApiResponse> handleServerException(EmployeeServiceServerException ex,
      WebRequest request) {
    exceptionlogger.error("Exception occured in Employee service", ex);
    return new ResponseEntity<ApiResponse>(new ApiResponse(RestApiResponseStatus.ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler
  protected ResponseEntity<ApiResponse> handleIllegalStateException(IllegalStateException ex,
      WebRequest request) {
    exceptionlogger.error("IllegalStateException occured", ex);
    return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler
  protected ResponseEntity<ApiResponse> handleDBException(EmployeeServiceDBException ex,
      WebRequest request) {
    exceptionlogger.error("EmployeeServiceDBException occured", ex);
    return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler
  protected ResponseEntity<ApiResponse> handleForbiddenException(EmployeeForbiddenException ex,
      WebRequest request) {
    exceptionlogger.error("EmployeeServiceForbiddenException occured", ex);
    return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.FORBIDDEN),
        HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> handleExceptions(Exception ex, WebRequest request) {
    exceptionlogger.error("General exception occured", ex);
    return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    exceptionlogger.error("Invalid method arguments", ex);
    BindingResult results = ex.getBindingResult();

    List<ValidationFailure> validationErrors = results.getFieldErrors().stream()
        .map(item -> new ValidationFailure(item.getField(), item.getDefaultMessage()))
        .collect(Collectors.toList());

    return handleExceptionInternal(ex, new ValidationFailureResponse(validationErrors), headers,
        status, request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    exceptionlogger.error("Bind errors", ex);
    BindingResult results = ex.getBindingResult();

    List<ValidationFailure> validationErrors = results.getFieldErrors().stream()
        .map(item -> new ValidationFailure(item.getField(), item.getDefaultMessage()))
        .collect(Collectors.toList());

    return handleExceptionInternal(ex, new ValidationFailureResponse(validationErrors), headers,
        status, request);
  }

}
