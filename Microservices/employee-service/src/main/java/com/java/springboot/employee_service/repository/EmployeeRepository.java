package com.java.springboot.employee_service.repository;

import com.java.springboot.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

   Optional<Employee> findEmployeeById(Long employeeId);

   Optional<Employee> findByEmail(String email);
}
