package com.employee.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.EndpointURI;
import com.employee.data.dto.EmployeeRequestDto;
import com.employee.data.dto.EmployeeResponseDto;
import com.employee.data.entities.Employee;
import com.employee.data.mapper.Mapper;
import com.employee.rest.enums.RestApiResponseStatus;
import com.employee.rest.response.BasicResponse;
import com.employee.rest.response.ContentResponse;
import com.employee.rest.response.ValidationFailureResponse;
import com.employee.server.services.EmployeeService;
import com.employee.util.Constants;
import com.employee.util.ValidationFailureStatusCodes;
@CrossOrigin
@RestController
public class EmployeeController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(EmployeeController.class);

  @PostMapping(value = EndpointURI.EMPLOYEES)
  public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto) {
    if (employeeService.isEmailExist(employeeDto.getEmail())) {
      logger.debug("email is already exists: createEmployee(), isEmailAlreadyExist: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_EMAIL,
          validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = mapper.map(employeeDto, Employee.class);
    employeeService.saveEmployee(employee);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMPLOYEE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EMPLOYEES)
  public ResponseEntity<Object> getAllEmployees() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEES,
        mapper.map(employeeService.getAllEmployees(), EmployeeResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.EMPLOYEE_BY_ID)
  public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
    if (employeeService.isEmployeeExist(id)) {
      logger.debug("delete employee by id");
      employeeService.deleteEmployee(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.EMPLOYEE_DELETED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.EMPLOYEES)
  public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto) {
    if (employeeService.isEmployeeExist(employeeDto.getId())) {
      if (employeeService.isUpdatedEmployeeEmailExist(employeeDto.getId(),
          employeeDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_EMAIL,
            validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      employeeService.saveEmployee(mapper.map(employeeDto, Employee.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EMPLOYEE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Employee By Id
  @GetMapping(value = EndpointURI.EMPLOYEE_BY_ID)
  public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
    if (employeeService.isEmployeeExist(id)) {
      logger.debug("Get Employee By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEE,
          mapper.map(employeeService.getEmployeeById(id), EmployeeResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }

}
