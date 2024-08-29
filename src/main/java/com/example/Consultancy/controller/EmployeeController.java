package com.example.Consultancy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Consultancy.entity.employee;
import com.example.Consultancy.repository.EmployeeRepository;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepo;

    @PostMapping("/api/employees")
    public ResponseEntity<employee> saveEmployee(@RequestBody employee employee) {
        return new ResponseEntity<>(employeeRepo.save(employee), HttpStatus.CREATED);
    }

    
    @PostMapping("api/employees/batchIntake")
    public ResponseEntity<List<employee>> saveEmployees(@RequestBody List<employee> employees) {
        return new ResponseEntity<>(employeeRepo.saveAll(employees), HttpStatus.CREATED);
    }
    
    @GetMapping("/api/employees")
    public ResponseEntity<List<employee>> getEmployees() {
        return new ResponseEntity<>(employeeRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<employee> getEmployeeById(@PathVariable long id) {
        Optional<employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/employees/{id}")
    public ResponseEntity<employee> updateEmployeeById(@PathVariable long id, @RequestBody employee emp) {
        Optional<employee> existingEmployee = employeeRepo.findById(id);
        if (existingEmployee.isPresent()) {
            employee employee = existingEmployee.get();
            employee.setName(emp.getName());
            employee.setEmail(emp.getEmail());
            employee.setDepartment(emp.getDepartment());
            return new ResponseEntity<>(employeeRepo.save(employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable long id) {
        Optional<employee> existingEmployee = employeeRepo.findById(id);
        if (existingEmployee.isPresent()) {
            employeeRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
