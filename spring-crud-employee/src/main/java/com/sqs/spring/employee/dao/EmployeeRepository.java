package com.sqs.spring.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqs.spring.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
