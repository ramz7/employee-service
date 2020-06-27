package com.employee.server.services;

import java.util.List;
import com.employee.data.entities.Employee;

public interface EmployeeService {
  public void createEmployee(Employee employee);

  public void updateEmployee(Employee employee);

  public boolean isEmailExist(String email);

  public void deleteEmployee(Long id);

  boolean isEmployeeExist(Long id);

  public Employee getEmployeeById(Long id);

  public boolean isUpdatedEmployeeEmailExist(Long id, String email);

  public List<Employee> getAllEmployees();
}
