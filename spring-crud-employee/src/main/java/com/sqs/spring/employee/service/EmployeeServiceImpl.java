package com.sqs.spring.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sqs.spring.employee.dao.EmployeeRepository;
import com.sqs.spring.employee.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
	return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {

	Optional<Employee> result = employeeRepository.findById(theId);

	Employee theEmployee = null;

	if (result.isPresent()) {
	    theEmployee = result.get();
	} else {
	    throw new RuntimeException("Did not find employee id - " + theId);
	}

	return theEmployee;
    }

    // @Transactional no need, JpaRepository provides this functionality.
    @Override
    public Employee save(Employee theEmployee) {
	return employeeRepository.save(theEmployee);
    }

    // @Transactional no need, JpaRepository provides this functionality.
    @Override
    public void deleteById(int theId) {
	employeeRepository.deleteById(theId);
    }

}
