package com.employee.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeRequestDto {
  private Long id;
  @NotNull(message = "{employeeDto.firstName.null}")
  @NotEmpty(message = "{employeeDto.firstName.empty}")
  @Pattern(regexp = "[a-z-A-Z]*", message = "{employeeDto.firstName.specialcharacter}")
  private String firstName;
  @NotNull(message = "{employeeDto.lastName.null}")
  @NotEmpty(message = "{employeeDto.lastName.empty}")
  private String lastName;
  @NotNull(message = "{employeeDto.email.null}")
  @NotEmpty(message = "{employeeDto.email.empty}")
  private String email;
  private String phoneNumber;
  private String address;
  private Long designationId;
  private Double salary;
  private Date hireDate;
  private String supervisorName;
  @NotNull(message = "{employeeDto.department.null}")
  @NotEmpty(message = "{employeeDto.department.empty}")
  private String department;
  @NotNull(message = "{employeeDto.nic.null}")
  @NotEmpty(message = "{employeeDto.nic.empty}")
  private String nic;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public String getSupervisorName() {
    return supervisorName;
  }

  public void setSupervisorName(String supervisorName) {
    this.supervisorName = supervisorName;
  }

  public Long getDesignationId() {
    return designationId;
  }

  public void setDesignationId(Long designationId) {
    this.designationId = designationId;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }
}
