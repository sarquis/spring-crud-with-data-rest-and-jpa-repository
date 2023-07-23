package com.sqs.spring.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqs.spring.employee.entity.Employee;

// @RepositoryRestResource is only needed if you want to change default plural name.
// FROM: /magic-api/employees TO: /magic-api/members 
// @RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
