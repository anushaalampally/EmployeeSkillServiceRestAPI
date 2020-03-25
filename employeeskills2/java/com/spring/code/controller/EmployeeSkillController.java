
  package com.spring.code.controller;
  
  import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RestController;

import com.spring.code.dao.EmployeeRepository;
import com.spring.code.entity.Employee;
import com.spring.code.service.EmployeeService;
  
  @RestController
  @RequestMapping("/api")
  public class EmployeeSkillController {
	  
	  private EmployeeService employeeService;
  
	@RequestMapping("/hello")
	public String index() {
		System.out.println("Spring Boot Example!!");
		return "Spring Boot Example!!";

	}

	
	@Autowired
	public EmployeeSkillController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

  
  }
  
 