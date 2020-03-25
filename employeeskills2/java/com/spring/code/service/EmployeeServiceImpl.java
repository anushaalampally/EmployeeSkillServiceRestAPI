package com.spring.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.code.dao.EmployeeRepository;
import com.spring.code.entity.Employee;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	
private EmployeeRepository employeeRepository;

@Autowired
public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
	employeeRepository = theEmployeeRepository;
}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	
}
