package com.sqs.spring.employee.service;

import java.util.List;

import com.sqs.spring.employee.entity.Employee;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
