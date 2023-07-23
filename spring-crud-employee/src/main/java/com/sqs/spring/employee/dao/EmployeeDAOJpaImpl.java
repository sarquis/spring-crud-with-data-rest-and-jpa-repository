package com.sqs.spring.employee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sqs.spring.employee.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    // @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

	TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

	List<Employee> employees = theQuery.getResultList();

	return employees;
    }

    @Override
    public Employee findById(int theId) {

	Employee theEmployee = entityManager.find(Employee.class, theId);

	return theEmployee;
    }

    // We don't use @Transaction at DAO layer, it will be handled at Service layer.
    @Override
    public Employee save(Employee theEmployee) {

	// Merge: if id == 0 then insert/save else update.
	Employee dbEmployee = entityManager.merge(theEmployee);

	// It has updated id from the database.
	return dbEmployee;
    }

    // We don't use @Transaction at DAO layer, it will be handled at Service layer.
    @Override
    public void deleteById(int theId) {

	Employee theEmployee = entityManager.find(Employee.class, theId);

	entityManager.remove(theEmployee);
    }

}
