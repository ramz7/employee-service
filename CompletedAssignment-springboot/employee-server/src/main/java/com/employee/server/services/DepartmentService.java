package com.employee.server.services;

import java.util.List;
import com.employee.data.entities.Department;

public interface DepartmentService {
  public List<Department> getAllDepartment();

  public boolean isDepartmentExist(Long id);

  public Department getDepartmentById(Long id);

  public void deleteDepartment(Long id);

  public void saveDepartment(Department department);

  public boolean isDepartmentExist(String department);

  public boolean isUpdatedDepartmentNameExist(Long id, String departmentName);
}
