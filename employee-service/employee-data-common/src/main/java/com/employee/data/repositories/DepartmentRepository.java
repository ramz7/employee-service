package com.employee.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.data.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  boolean existsByName(String department);
}
