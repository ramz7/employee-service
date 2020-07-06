package com.employee.server.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.data.entities.Employee;
import com.employee.data.repositories.EmployeeRepository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  EmployeeRepository employeeRepository;

  @Transactional
  public void saveEmployee(Employee employee) {
	  employeeRepository.save(employee);
  }

  @Transactional(readOnly = true)
  public boolean isEmailExist(String email) {
	  return employeeRepository.existsByEmail(email);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEmployee(Long id) {
	  employeeRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isEmployeeExist(Long id) {
	  return employeeRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Employee getEmployeeById(Long id) {
	  return employeeRepository.findById(id).get();
  }

  public boolean isUpdatedEmployeeEmailExist(Long id, String email) {
	  if ((!getEmployeeById(id).getEmail().equalsIgnoreCase(email)) && (isEmailExist(email))) {
	      return true;
	    }
	    return false;
  }

  @Transactional(readOnly = true)
  public List<Employee> getAllEmployees() {
	  return  employeeRepository.findAll();
  }
}
