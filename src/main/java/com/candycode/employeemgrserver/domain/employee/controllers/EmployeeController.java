package com.candycode.employeemgrserver.domain.employee.controllers;

import com.candycode.employeemgrserver.domain.employee.models.Employee;
import com.candycode.employeemgrserver.domain.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")//V1 allows to upgrade or change api, but still support previous versions. Creates versions for each controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired//Look for any object that implements employee service
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping//Creation is done with a post request
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = employeeService.create(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);

    }

    @GetMapping("{id}")//Cannot have 2 getMappings with the same API.
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("lookup")
    public ResponseEntity<Employee> getByEmail(@RequestParam String email){
        Employee employee = employeeService.getByEmail(email);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee>update(@PathVariable("id")Long id,@RequestBody Employee employeeDetail){
        employeeDetail = employeeService.update(id, employeeDetail);
        return new ResponseEntity<>(employeeDetail, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        employeeService.delete(id);
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }
}


