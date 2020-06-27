package com.employee.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.data.entities.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {

	boolean existsByName(String name);

}