package com.employee.server.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.employee.data.entities.EmployeeFamilyDetail;
import com.employee.data.repositories.EmployeeFamilyDetailsRepository;

@Service
public class EmployeeFamilyDetailsServiceImpl implements EmployeeFamilyDetailsService {
  @Autowired
  private EmployeeFamilyDetailsRepository employeeFamilyDetailsRepository;

  @Transactional
  public void saveEmployeeFamilyDetails(EmployeeFamilyDetail employeeFamilyDetail) {
    employeeFamilyDetailsRepository.save(employeeFamilyDetail);
  }

  @Transactional(readOnly = true)
  public List<EmployeeFamilyDetail> getAllEmployeeFamilyDetails() {
    return employeeFamilyDetailsRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isEmployeeFamilyDetailsExist(Long id) {
    return employeeFamilyDetailsRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEmployeeFamilyDetail(Long id) {
    employeeFamilyDetailsRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public EmployeeFamilyDetail getEmployeeFamilyDetail(Long id) {
   return employeeFamilyDetailsRepository.findById(id).get();
  }
}
