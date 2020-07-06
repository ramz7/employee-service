package com.employee.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class DesignationDto {
  private Long id;
  @NotNull(message = "{designationDto.name.null}")
  @NotEmpty(message = "{designationDto.name.empty}")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
