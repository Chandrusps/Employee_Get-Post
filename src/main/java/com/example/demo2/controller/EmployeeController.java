package com.example.demo2.controller;

import com.example.demo2.model.Employee;
import com.example.demo2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/emp")
public class EmployeeController {


    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/empget/{id}")
    public ResponseEntity<Employee> getStudentById(@PathVariable("id") long id)
    {
        Optional<Employee> studentData = employeeRepository.findById(id);
        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value ="/empGetAll",method=RequestMethod.GET)
    public List<Employee> getEmloyee(){
        return employeeRepository.findAll();
       // System.out.println("Success");
        // System.out.println("Success");
        // System.out.println("Success");jhjhgj

    }

    @PostMapping("/emppost")
    public ResponseEntity<Employee> createTutorial(@RequestBody Employee employee) {
        try {
            Employee emp = employeeRepository
                    .save(new Employee(employee.getEmp_id(),employee.getEmp_name(), employee.getEmp_address(), employee.getEmp_salary()));
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
