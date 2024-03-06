package com.example.resttemplate.service;


import com.example.resttemplate.model.Employee;

import java.util.List;


public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> findAllEmployee();
    Employee findEmployeeById(Integer id);
    Employee deleteEmployeeById(Integer  id);
    Employee updateEmployee(Integer id ,Employee employee);

}
