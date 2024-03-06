package com.example.resttemplate.controller;

import com.example.resttemplate.model.Employee;
import com.example.resttemplate.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private  final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping ("/employees")
    public Employee saveEmployee(@RequestBody  Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/employees")
    public List<Employee> findAllEmployee() {
        return employeeService.findAllEmployee();
    }
    @GetMapping("/employees/{id}")
    public Employee findEmployeeById(@PathVariable("id")  Integer id) {
        return employeeService.findEmployeeById(id);
    }
    @DeleteMapping("/employees/{id}")
    public Employee deleteEmployeeByID(@PathVariable("id") Integer id){
        return employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

}
