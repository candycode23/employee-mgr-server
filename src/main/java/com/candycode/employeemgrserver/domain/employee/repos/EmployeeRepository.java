package com.candycode.employeemgrserver.domain.employee.repos;

import com.candycode.employeemgrserver.domain.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;// optional is never null.Safe way to handle date not existing.

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //employee and long are the two generic representatives, model and type of id.
    Optional<Employee> findByEmail(String email);
}
