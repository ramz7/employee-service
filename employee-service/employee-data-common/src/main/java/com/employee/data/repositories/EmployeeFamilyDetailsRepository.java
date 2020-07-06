package com.employee.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.data.entities.EmployeeFamilyDetail;

public interface EmployeeFamilyDetailsRepository  extends JpaRepository<EmployeeFamilyDetail, Long> {

}
