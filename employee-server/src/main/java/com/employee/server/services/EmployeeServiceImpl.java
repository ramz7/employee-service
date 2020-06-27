package com.employee.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.data.entities.Employee;
import com.employee.data.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public void createEmployee(Employee employee) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateEmployee(Employee employee) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isEmailExist(String email) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void deleteEmployee(Long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isEmployeeExist(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Employee getEmployeeById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isUpdatedEmployeeEmailExist(Long id, String email) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<Employee> getAllEmployees() {
    // TODO Auto-generated method stub
    return null;
  }



}
