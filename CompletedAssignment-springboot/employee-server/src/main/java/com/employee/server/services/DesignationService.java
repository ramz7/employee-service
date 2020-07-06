package com.employee.server.services;


import java.util.List;
import com.employee.data.entities.Designation;

public interface DesignationService {
  public List<Designation> getAllDesignations();

  public boolean isDesignationExist(Long id);

  public Designation getDesignationById(Long id);

  public void deleteDesignation(Long id);

  public void saveDesignation(Designation designation);

  public boolean isDesignationExist(String designation);

  public boolean isUpdatedDesignationNameExist(Long id, String designationName);

}
