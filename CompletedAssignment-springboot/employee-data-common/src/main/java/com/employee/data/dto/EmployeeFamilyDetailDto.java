package com.employee.data.dto;

public class EmployeeFamilyDetailDto {
  private Long id;
  private String fatherName;
  private String fatherJob;
  private String motherName;
  private String motherJob;
  private Long employeeId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public String getFatherJob() {
    return fatherJob;
  }

  public void setFatherJob(String fatherJob) {
    this.fatherJob = fatherJob;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public String getMotherJob() {
    return motherJob;
  }

  public void setMotherJob(String motherJob) {
    this.motherJob = motherJob;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }
}
