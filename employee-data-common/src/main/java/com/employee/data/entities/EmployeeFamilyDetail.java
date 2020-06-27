package com.employee.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "employee-system", name = "employee_family_detail")
public class EmployeeFamilyDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String fatherName;
  private String fatherJob;
  private String motherName;
  private String motherJob;

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
}
