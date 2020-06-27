package com.employee.data.dto;

public class EmployeeResponseDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String address;
  private String plantName;
  private String designationName;
  private String plantCode;
  private Long designationId;
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

  public String getPlantName() {
    return plantName;
  }

  public void setPlantName(String plantName) {
    this.plantName = plantName;
  }

  public String getDesignationName() {
    return designationName;
  }

  public void setDesignationName(String designationName) {
    this.designationName = designationName;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public Long getDesignationId() {
    return designationId;
  }

  public void setDesignationId(Long designationId) {
    this.designationId = designationId;
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }
}
