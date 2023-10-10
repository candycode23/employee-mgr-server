package com.candycode.employeemgrserver.domain.employee.services;

import com.candycode.employeemgrserver.domain.core.exceptions.ResourceCreationException;
import com.candycode.employeemgrserver.domain.core.exceptions.ResourceNotFoundException;
import com.candycode.employeemgrserver.domain.employee.models.Employee;

import javax.persistence.Id;
import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee)throws ResourceCreationException;
    Employee getById(Long Id) throws ResourceCreationException;
    Employee getByEmail (String email) throws ResourceNotFoundException;
    List<Employee> getAll();
    Employee update(Long Id,Employee employeeDetail) throws ResourceNotFoundException;
    void delete(Long id);
}
