package com.employee.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.data.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  boolean existsByEmail(String mail);

}
