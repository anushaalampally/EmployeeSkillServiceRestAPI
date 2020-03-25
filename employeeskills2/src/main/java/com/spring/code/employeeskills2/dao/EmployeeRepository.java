package com.spring.code.employeeskills2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.code.employeeskills2.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
