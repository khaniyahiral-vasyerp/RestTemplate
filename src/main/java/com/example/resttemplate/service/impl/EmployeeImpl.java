package com.example.resttemplate.service.impl;

import com.example.resttemplate.Repository.EmployeeRepository;
import com.example.resttemplate.model.Employee;
import com.example.resttemplate.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("employee with  id"+ id + " not found !"));
    }

    @Override
    public Employee deleteEmployeeById(Integer id) {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee existingEmployee=findEmployeeById(id);
        if(isFieldUpdatable(employee.getName(),existingEmployee.getName()))
        {
            existingEmployee.setName(employee.getName());
        }
        if(isFieldUpdatable(employee.getEmail(),existingEmployee.getEmail()))
        {
            existingEmployee.setEmail(employee.getEmail());
        }
        return employeeRepository.save(existingEmployee);
    }

    private boolean isFieldUpdatable(String newField, String oldField) {
        return newField !=null && !newField.isEmpty() && !newField.equals(oldField);
    }
}
