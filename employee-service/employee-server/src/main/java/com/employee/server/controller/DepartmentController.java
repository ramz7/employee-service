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
import com.employee.data.dto.DepartmentDto;
import com.employee.data.entities.Department;
import com.employee.data.mapper.Mapper;
import com.employee.rest.enums.RestApiResponseStatus;
import com.employee.rest.response.BasicResponse;
import com.employee.rest.response.ContentResponse;
import com.employee.rest.response.ValidationFailureResponse;
import com.employee.rest.validation.ValidationFailure;
import com.employee.server.services.DepartmentService;
import com.employee.util.Constants;
import com.employee.util.ValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class DepartmentController {
  @Autowired
  DepartmentService departmentService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(DepartmentController.class);

  // POST DEPARTMENT
  @PostMapping(value = EndpointURI.DEPARTMENT)
  public ResponseEntity<Object> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
    if (departmentService.isDepartmentExist(departmentDto.getName())) {
      logger.debug("Department already exists: createDepartment(), departmentName: {}");
      return new ResponseEntity<>(new ContentResponse<>(Constants.DEPARTMENT,
          new ValidationFailure(Constants.DEPARTMENT_NAME,
              validationFailureStatusCodes.getDepartmentAlreadyExist()),
          RestApiResponseStatus.VALIDATION_FAILURE), HttpStatus.BAD_REQUEST);
    }
    departmentService.saveDepartment(mapper.map(departmentDto, Department.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_DEPARTMENT_SUCCESS),
        HttpStatus.OK);
  }

  // GET ALL DEPARTMENT
  @GetMapping(value = EndpointURI.DEPARTMENTS)
  public ResponseEntity<Object> getAllDepartments() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.DEPARTMENTS,
        mapper.map(departmentService.getAllDepartment(), DepartmentDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // GET DEPARTMENT BY ID
  @GetMapping(value = EndpointURI.DEPARTMENT_BY_ID)
  public ResponseEntity<Object> getDepartmentById(@PathVariable Long id) {
    if (departmentService.isDepartmentExist(id)) {
      logger.debug("get department by id ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.DEPARTMENT,
              mapper.map(departmentService.getDepartmentById(id), DepartmentDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DEPARTMENT,
        validationFailureStatusCodes.getDepartmentNotExist()), HttpStatus.BAD_REQUEST);

  }

  // DELETE DEPARTMENT
  @DeleteMapping(value = EndpointURI.DEPARTMENT_BY_ID)
  public ResponseEntity<Object> deleteDepartment(@PathVariable Long id) {
    if (departmentService.isDepartmentExist(id)) {
      departmentService.deleteDepartment(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DEPARTMENT_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DEPARTMENT_ID,
        validationFailureStatusCodes.getDepartmentNotExist()), HttpStatus.BAD_REQUEST);
  
  }

  // UPDATE DEPARTMENT
  @PutMapping(value = EndpointURI.DEPARTMENT)
  public ResponseEntity<Object> updateDepartment(
      @Valid @RequestBody DepartmentDto departmentDto) {
    if (departmentService.isDepartmentExist(departmentDto.getId())) {
      if (departmentService.isUpdatedDepartmentNameExist(departmentDto.getId(),
          departmentDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.DEPARTMENT_NAME,
            validationFailureStatusCodes.getDepartmentAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      departmentService.saveDepartment(mapper.map(departmentDto, Department.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_DEPARTMENT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DEPARTMENT_NAME,
        validationFailureStatusCodes.getDepartmentNotExist()), HttpStatus.BAD_REQUEST);
  }
}
