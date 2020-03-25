package com.spring.code.employeeskills1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.spring.code"})
//@EnableJpaRepositories("com.spring.code.dao.EmployeeRepository")
public class Employeeskills1Application {

	public static void main(String[] args) {
		SpringApplication.run(Employeeskills1Application.class, args);
	}

}
