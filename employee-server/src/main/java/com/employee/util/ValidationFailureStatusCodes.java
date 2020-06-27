package com.employee.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * contains custom error messages
 *
 */

@Component
@PropertySource("classpath:ValidationMessages.properties")
public class ValidationFailureStatusCodes {
  // for designation
  @Value("${validation.designation.notExists}")
  private String designationNotExist;

  @Value("${validation.designation.alreadyExist}")
  private String designationAlreadyExist;

  public String getDesignationNotExist() {
    return designationNotExist;
  }

  public void setDesignationNotExist(String designationNotExist) {
    this.designationNotExist = designationNotExist;
  }

  public String getDesignationAlreadyExist() {
    return designationAlreadyExist;
  }

  public void setDesignationAlreadyExist(String designationAlreadyExist) {
    this.designationAlreadyExist = designationAlreadyExist;
  }

  // Employee
  @Value("${validation.employee.notExists}")
  private String employeeNotExist;

  @Value("${validation.employee.alreadyExist}")
  private String employeeAlreadyExist;

  public String getEmployeeNotExist() {
    return employeeNotExist;
  }

  public void setEmployeeNotExist(String employeeNotExist) {
    this.employeeNotExist = employeeNotExist;
  }

  public String getEmployeeAlreadyExist() {
    return employeeAlreadyExist;
  }

  public void setEmployeeAlreadyExist(String employeeAlreadyExist) {
    this.employeeAlreadyExist = employeeAlreadyExist;
  }

  @Value("${validation.department.notExists}")
  private String departmentNotExist;

  @Value("${validation.department.alreadyExist}")
  private String departmentAlreadyExist;

  public String getDepartmentNotExist() {
    return departmentNotExist;
  }

  public void setDepartmentNotExist(String departmentNotExist) {
    this.departmentNotExist = departmentNotExist;
  }

  public String getDepartmentAlreadyExist() {
    return departmentAlreadyExist;
  }

  public void setDepartmentAlreadyExist(String departmentAlreadyExist) {
    this.departmentAlreadyExist = departmentAlreadyExist;
  }
}
