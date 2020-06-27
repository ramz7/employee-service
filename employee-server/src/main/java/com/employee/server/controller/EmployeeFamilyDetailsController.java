package com.employee.server.controller;

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
import com.employee.data.dto.EmployeeFamilyDetailDto;
import com.employee.data.entities.EmployeeFamilyDetail;
import com.employee.data.mapper.Mapper;
import com.employee.rest.enums.RestApiResponseStatus;
import com.employee.rest.response.BasicResponse;
import com.employee.rest.response.ContentResponse;
import com.employee.rest.response.ValidationFailureResponse;
import com.employee.server.services.EmployeeFamilyDetailsService;
import com.employee.util.Constants;
import com.employee.util.ValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class EmployeeFamilyDetailsController {
  @Autowired
  private EmployeeFamilyDetailsService employeeFamilyDetailsService;
  @Autowired
  private Mapper mapper;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(EmployeeFamilyDetailsController.class);
 
  @PostMapping(value = EndpointURI.EMPLOYEE_FAMILY_DETAIL)
  public ResponseEntity<Object> createEmployeeFamilyDetails(
      @RequestBody EmployeeFamilyDetailDto employeeFamilyDetailDto) {
    employeeFamilyDetailsService
        .saveEmployeeFamilyDetails(mapper.map(employeeFamilyDetailDto, EmployeeFamilyDetail.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMPLOYEE_FAMILY_DETAIL_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EMPLOYEE_FAMILY_DETAILS)
  public ResponseEntity<Object> getAllEmployeeFamilyDetails() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEE_FAMILY_DETAILS,
        mapper.map(employeeFamilyDetailsService.getAllEmployeeFamilyDetails(),
            EmployeeFamilyDetail.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.EMPLOYEE_FAMILY_DETAIL_BY_ID)
  public ResponseEntity<Object> deleteEmployeeFamilyDetails(@PathVariable Long id) {
    if (employeeFamilyDetailsService.isEmployeeFamilyDetailsExist(id)) {
      logger.debug("delete employee family by id");
      employeeFamilyDetailsService.deleteEmployeeFamilyDetail(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.EMPLOYEE_FAMILY_DETAIL_DELETED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_FAMILY_DETAIL_ID,
        validationFailureStatusCodes.getFamilyDetailsNotExist()), HttpStatus.BAD_REQUEST);
  }
  @PutMapping(value = EndpointURI.EMPLOYEE_FAMILY_DETAIL)
  public ResponseEntity<Object> updateEmployeeFamilyDetails(@RequestBody EmployeeFamilyDetailDto employeeFamilyDetailDto) {
    if (employeeFamilyDetailsService.isEmployeeFamilyDetailsExist(employeeFamilyDetailDto.getId())) {
      employeeFamilyDetailsService.saveEmployeeFamilyDetails(mapper.map(employeeFamilyDetailDto, EmployeeFamilyDetail.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EMPLOYEE_FAMILY_DETAIL_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_FAMILY_DETAIL_ID,
        validationFailureStatusCodes.getFamilyDetailsNotExist()), HttpStatus.BAD_REQUEST);
  }
  @GetMapping(value = EndpointURI.EMPLOYEE_FAMILY_DETAIL_BY_ID)
  public ResponseEntity<Object> getEmployeeFamilyDetails(@PathVariable Long id) {
    if (employeeFamilyDetailsService.isEmployeeFamilyDetailsExist(id)) {
      logger.debug("delete employee family by id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEE_FAMILY_DETAILS,
          mapper.map(employeeFamilyDetailsService.getEmployeeFamilyDetail(id),
              EmployeeFamilyDetail.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_FAMILY_DETAIL_ID,
        validationFailureStatusCodes.getFamilyDetailsNotExist()), HttpStatus.BAD_REQUEST);
  }
  
}
