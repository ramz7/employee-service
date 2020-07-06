package com.employee.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.employee.data.entities.Department;
import com.employee.data.repositories.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
  @Autowired
  private DepartmentRepository departmentRepository;

  @Transactional(readOnly = true)
  public List<Department> getAllDepartment() {
    return departmentRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isDepartmentExist(Long id) {
    return departmentRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Department getDepartmentById(Long id) {
    return departmentRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteDepartment(Long id) {
    departmentRepository.deleteById(id);
  }

  @Transactional
  public void saveDepartment(Department department) {
    departmentRepository.save(department);
  }

  @Transactional(readOnly = true)
  public boolean isDepartmentExist(String department) {
    return departmentRepository.existsByName(department);
  }

  public boolean isUpdatedDepartmentNameExist(Long id, String departmentName) {
    if ((!getDepartmentById(id).getName().equalsIgnoreCase(departmentName))
        && (isDepartmentExist(departmentName))) {
      return true;
    }
    return false;
  }
}
