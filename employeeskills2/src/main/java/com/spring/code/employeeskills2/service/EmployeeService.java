package com.spring.code.employeeskills2.service;

import java.util.List;

import com.spring.code.employeeskills2.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAllEmployees();
	public Employee createEmployee(Employee theEmployee);
	
	
	
	
	public Employee findEmployeeById(String theId);
	public Employee updateEmployeeById(Employee theEmployee);
	public void removeEmployeeById(String theId);
	
}
