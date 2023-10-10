package com.candycode.employeemgrserver.domain.employee.services;

import com.candycode.employeemgrserver.domain.core.exceptions.ResourceCreationException;
import com.candycode.employeemgrserver.domain.core.exceptions.ResourceNotFoundException;
import com.candycode.employeemgrserver.domain.employee.models.Employee;
import com.candycode.employeemgrserver.domain.employee.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//to create object
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;


@Autowired//will tell spring container to grab whatever employee repository is in the container
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) throws ResourceCreationException {
        Optional<Employee>optional = employeeRepository.findByEmail(employee.getEmail());
        if(optional.isPresent())
            throw new ResourceCreationException("Employee with email exist: " + employee.getEmail());
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getById(Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No employee with id: " + id));
        return employee;
    }

    @Override
    public Employee getByEmail(String email) throws ResourceNotFoundException {
    Employee employee = employeeRepository.findByEmail(email)
            .orElseThrow(()->new ResourceNotFoundException("No Employee with email: " + email));
        return employee;
    }

    @Override
    public List<Employee> getAll() {
       return employeeRepository.findAll();
    }

    @Override
    public Employee update(Long id, Employee employeeDetail) throws ResourceNotFoundException {
    Employee employee = employeeRepository.getById(id);
    employee.setFirstName(employeeDetail.getFirstName());
    employee.setLastName(employeeDetail.getLastName());
    employee.setEmail(employeeDetail.getEmail());
    employee = employeeRepository.save(employee);
    return employee;
    }

    @Override
    public void delete(Long id) {
    Employee employee = getById(id);
    employeeRepository.delete(employee);
    }
}
