package com.employee.server.services;

import java.util.List;
import com.employee.data.entities.EmployeeFamilyDetail;

public interface EmployeeFamilyDetailsService {

  public void saveEmployeeFamilyDetails(EmployeeFamilyDetail employeeFamilyDetail);

  public List<EmployeeFamilyDetail> getAllEmployeeFamilyDetails();

  public boolean isEmployeeFamilyDetailsExist(Long id);

  public void deleteEmployeeFamilyDetail(Long id);

  public EmployeeFamilyDetail getEmployeeFamilyDetail(Long id);


}
