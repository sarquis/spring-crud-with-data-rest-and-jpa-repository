package com.sqs.spring.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sqs.spring.employee.dao.EmployeeDAO;
import com.sqs.spring.employee.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    // @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
	this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
	return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int theId) {
	return employeeDAO.findById(theId);
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
	return employeeDAO.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
	employeeDAO.deleteById(theId);
    }

}
