package com.spring.code.employeeskills2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.code.employeeskills2.dao.EmployeeRepository;
import com.spring.code.employeeskills2.entity.Employee;
@Service
public class EmployeeServiceImp implements EmployeeService {
	@Autowired
 private EmployeeRepository employeeRepository;
	@Override
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
	@Override
	public Employee createEmployee(Employee theEmployee) {
		
		Employee employee=	employeeRepository.save(theEmployee);
		
		return employee;
	}
	@Override
	public Employee findEmployeeById(String theId) {
		
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		// TODO Auto-generated method stub
		return theEmployee;
	}
	public Employee updateEmployeeById(Employee theEmployee) {
		return employeeRepository.save(theEmployee);
	}
	@Override
	public void removeEmployeeById(String theId) {
		employeeRepository.deleteById(theId);
		
	}
	
}
