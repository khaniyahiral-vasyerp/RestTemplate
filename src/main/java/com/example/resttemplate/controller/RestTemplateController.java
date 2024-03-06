package com.example.resttemplate.controller;

import com.example.resttemplate.model.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class RestTemplateController {
    private final RestTemplate restTemplate;

    public RestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String BASE_URL = "http://localhost:8080/api";
    private final String URI_EMPLOYEES = BASE_URL + "/employees";
    private final String URI_EMPLOYEES_ID = BASE_URL + "/employees/{id}";

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URI_EMPLOYEES, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Employee>>() {

        });
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URI_EMPLOYEES_ID, HttpMethod.GET, entity, Employee.class, id);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployees(@RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(employee, headers);
        return restTemplate.exchange(URI_EMPLOYEES, HttpMethod.POST, entity, Employee.class);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URI_EMPLOYEES_ID, HttpMethod.DELETE, entity, Employee.class, id);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(employee, headers);
        return restTemplate.exchange(URI_EMPLOYEES_ID, HttpMethod.PUT, entity, Employee.class, id);
    }


}
