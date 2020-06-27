package com.employee.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.EndpointURI;
import com.employee.data.dto.DesignationDto;
import com.employee.data.entities.Designation;
import com.employee.data.mapper.Mapper;
import com.employee.rest.enums.RestApiResponseStatus;
import com.employee.rest.response.BasicResponse;
import com.employee.rest.response.ContentResponse;
import com.employee.rest.response.ValidationFailureResponse;
import com.employee.rest.validation.ValidationFailure;
import com.employee.server.services.DesignationService;
import com.employee.util.Constants;
import com.employee.util.ValidationConstance;
import com.employee.util.ValidationFailureStatusCodes;

@RestController
public class DesignationController {
  @Autowired
  DesignationService designationService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(DesignationController.class);

  // ADD DESIGNATION
  @PostMapping(value = EndpointURI.DESIGNATION)
  public ResponseEntity<Object> createDesignation(
      @Valid @RequestBody DesignationDto designationDto) {
    if (designationService.isDesignationExist(designationDto.getName())) {
      logger.debug("Designation already exists: createDesignation(), dsesignationName: {}");
      return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATION,
          new ValidationFailure(Constants.DESIGNATION_NAME,
              validationFailureStatusCodes.getDesignationAlreadyExist()),
          RestApiResponseStatus.VALIDATION_FAILURE), HttpStatus.BAD_REQUEST);
    }
    designationService.saveDesignation(mapper.map(designationDto, Designation.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_DESIGNATION_SUCCESS),
        HttpStatus.OK);
  }

  // GET ALL DESIGNATIONS
  @GetMapping(value = EndpointURI.DESIGNATIONS)
  public ResponseEntity<Object> getAllDesignations() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.DESIGNATIONS,
        mapper.map(designationService.getAllDesignations(), DesignationDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // GET DESIGNATION BY ID
  @GetMapping(value = EndpointURI.DESIGNATION_BY_ID)
  public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
    if (designationService.isDesignationExist(id)) {
      logger.debug("get designation by id ");
      Designation designation = designationService.getDesignationById(id);
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.DESIGNATION,
              mapper.map(designation, DesignationDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION,
        validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);

  }

  // DELETE DESIGNATION
  @DeleteMapping(value = EndpointURI.DESIGNATION_BY_ID)
  public ResponseEntity<Object> deleteDesignation(@PathVariable Long id) {
    if (designationService.isDesignationExist(id)) {
      designationService.deleteDesignation(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DESIGNATION_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new BasicResponse<>(
            new ValidationFailure(Constants.DESIGNATION_NAME,
                validationFailureStatusCodes.getDesignationNotExist()),
            RestApiResponseStatus.VALIDATION_FAILURE, ValidationConstance.DESIGNATION_NOT_EXIST),
        HttpStatus.BAD_REQUEST);
  }

  // UPDATE DESIGNATION
  @PutMapping(value = EndpointURI.DESIGNATION)
  public ResponseEntity<Object> updateDesignation(
      @Valid @RequestBody DesignationDto designationDto) {
    if (designationService.isDesignationExist(designationDto.getId())) {
      if (designationService.isUpdatedDesignationNameExist(designationDto.getId(),
          designationDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NAME,
            validationFailureStatusCodes.getDesignationAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      designationService.saveDesignation(mapper.map(designationDto, Designation.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_DESIGNATION_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NAME,
        validationFailureStatusCodes.getDesignationNotExist()), HttpStatus.BAD_REQUEST);
  }
}
