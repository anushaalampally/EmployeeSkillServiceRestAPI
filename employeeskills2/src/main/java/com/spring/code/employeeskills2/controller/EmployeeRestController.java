package com.spring.code.employeeskills2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.code.employeeskills2.Constants.EmployeeSkillConstants;
import com.spring.code.employeeskills2.entity.Employee;
import com.spring.code.employeeskills2.entity.Skill;
import com.spring.code.employeeskills2.service.EmployeeService;
import com.spring.code.employeeskills2.service.SkillService;
import com.spring.code.employeeskills2.util.EmployeeSkillUtil;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired 
	private SkillService skillService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Anusha is good";
	}
	// Get all Perficient employees.
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAllEmployees() {
		List<Employee> employees=employeeService.findAllEmployees();
		HttpHeaders headers= new HttpHeaders();
		String employeesize=String.valueOf(employees.size());
		headers.add("Count",employeesize);
		headers.add("description", EmployeeSkillConstants.TOTAL_COUNT_PERFICIENT_EMP_DESCRIPTION);
		if(employees.isEmpty()){
	        return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
	    }
		return new ResponseEntity<List<Employee>>(employees,headers, HttpStatus.OK);
	}
	//create a Perficient employee.
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee theEmployee) {
			
		try {
			Employee employee=employeeService.createEmployee(theEmployee);
			if(null!=employee) {
				return new ResponseEntity<Employee>(HttpStatus.CREATED);
			}else {
				return new ResponseEntity<Employee>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		   //Invalid Perficient employee data sent to server.		
			return new ResponseEntity<Employee>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}
	//Find a Perficient employee by ID.
	
		@GetMapping("/employees/{employeeId}")
		public ResponseEntity<Employee> findEmployeeById(@PathVariable String employeeId) {
			
			if(!EmployeeSkillUtil.isValidId(employeeId)) {
				return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			}
			Employee theEmployee = employeeService.findEmployeeById(employeeId);
			
			if(null!=theEmployee) {
				return new ResponseEntity<Employee>(theEmployee,HttpStatus.OK);
			}else {
				return  new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			}
			}

		//Update a Perficient employee by ID.
		@PutMapping("/employees/{employeeId}")
		public ResponseEntity<Employee> updateEmployeeById(@PathVariable String employeeId,@RequestBody Employee theEmployee) {
			try {
				if(!EmployeeSkillUtil.isValidId(employeeId)) {
					return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
				}
				Employee retrivedEmployee = employeeService.findEmployeeById(employeeId);
				
				//verifying employeeId with the request body employee ID
				if(null!=retrivedEmployee&&(retrivedEmployee.getId().equals(theEmployee.getId()))) {
					Employee updatedEmployee1=employeeService.updateEmployeeById(theEmployee);
					return new ResponseEntity<Employee>(updatedEmployee1,HttpStatus.OK);
				}else {
					return  new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				
				//e.printStackTrace();
				//Invalid Perficient employee data sent to server.i.e if the user want give proper data
				return new ResponseEntity<Employee>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
		}
		//Delete a Perficient employee by ID.
		@DeleteMapping("/employees/{employeeId}")
		public ResponseEntity<Void> removeEmployeeById(@PathVariable String employeeId) {
			
			if(!EmployeeSkillUtil.isValidId(employeeId)) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			Employee theEmployee = employeeService.findEmployeeById(employeeId);
			if(null!=theEmployee) {
				employeeService.removeEmployeeById(employeeId);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}else  {
				return  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
						
				}
		
	/*   --------------------All Skill related request-------------------   */
		// Get all technical skills from a Perficient employee.
		@GetMapping("/employees/{employeeId}/skills")
		public ResponseEntity<List<Skill>> findAllSkillsByEmployee(@PathVariable String employeeId) {
			
			Employee theEmployee = employeeService.findEmployeeById(employeeId);
			List<Skill> skills=theEmployee.getSkill();
			HttpHeaders headers= new HttpHeaders();
			String skillssize=String.valueOf(skills.size());
			headers.add("Count",skillssize);
			headers.add("description", "The total count of technical skills from a Perficient employees returned.");
			if(skills.isEmpty()){
		        return new ResponseEntity<List<Skill>>(HttpStatus.NO_CONTENT);
		    }
			return new ResponseEntity<List<Skill>>(skills,headers, HttpStatus.OK);
		}
		
	//	 Add a technical skill to a Perficient employee.		
			
	@PostMapping("/employees/{employeeId}/skills")
	public ResponseEntity<Skill> addSkillToEmployee(@PathVariable String employeeId, @RequestBody Skill skill) {
		try {
			if (!EmployeeSkillUtil.isValidId(employeeId)) {
				return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
			}
			
			// before  adding skill to employee checking in the databese if there is an employee with the given employeeId
			Employee employee = employeeService.findEmployeeById(employeeId);
			if (employee == null) {
				return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
			}
			
			// adding skill in Skill table
			Skill addedSkill = skillService.save(skill);
			
			// Mapping skill to skill to employee by adding to skill list.
			employee.getSkill().add(addedSkill);
			// after adding skill to employe we are updating database so that the skill will be added into employee_skill table
			employeeService.updateEmployeeById(employee);
			
			return new ResponseEntity<Skill>(HttpStatus.CREATED);
		} catch (Exception e) {		
			
			//System.out.println(e.getMessage());
			//Invalid Perficient employee data sent to server.i.e if the user want give proper data
			return new ResponseEntity<Skill>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}
	
		@GetMapping("/employees/{employeeId}/skills/{skillid}")
		public ResponseEntity<Skill> findSkillFromEmployeeById(@PathVariable String employeeId,@PathVariable String skillid) {
			if(!EmployeeSkillUtil.isValidId(employeeId)) {
				return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
			}
			if(!EmployeeSkillUtil.isValidId(skillid)) {
				return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
			}
			Employee theEmployee = employeeService.findEmployeeById(employeeId);
			// validating if there no employee exists in database
			if(null!=theEmployee) {
			List<Skill> skills=theEmployee.getSkill();
			Skill skill=null;
			for(int i=0;i<skills.size();i++) {
				if(skills.get(i).getSkillId().equals(skillid)) {
					skill=skills.get(i);
					return new ResponseEntity<Skill>(skill,HttpStatus.OK);
					
				}
			}
			}
			// This return statement will cover if Technical skill or Perficient employee not found.
			return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
			
}
			@PutMapping("/employees/{employeeId}/skills/{skillId}")
		public ResponseEntity<Skill> updateSkillFromEmployeeById(@PathVariable String employeeId,@PathVariable String skillId,@RequestBody Skill theSkill) {
			try {
				if(!EmployeeSkillUtil.isValidId(employeeId)) {
					return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
				}
				if(!EmployeeSkillUtil.isValidId(skillId)) {
					return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
				}
				Skill retrivedSkill=skillService.findSkillById(skillId);
				Employee theEmployee = employeeService.findEmployeeById(employeeId);
				
				// considering the case where Technical skill or Perficient employee not found.
				if (theEmployee == null||retrivedSkill==null) {
					return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
				}
				List<Skill> skills=theEmployee.getSkill();
				Skill updatedskill=null;
				for(int i=0;i<skills.size();i++) {
					
					// here we are verifiying if request Body(request) skill is matching in employee's existing skills
					if(skills.get(i).getSkillId().equals(theSkill.getSkillId())) {
						if(null!=retrivedSkill) {
							 updatedskill=skillService.save(theSkill);							
							// success
							return new ResponseEntity<Skill>(updatedskill,HttpStatus.OK);
							
							}
						
					}
				}
				return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
				
				
				
			} catch (Exception e) {
				
				//e.printStackTrace();
				
				return new ResponseEntity<Skill>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
		}
	//'Delete a technical skill, from a Perficient employee, by ID.'
	@DeleteMapping("/employees/{employeeId}/skills/{skillid}")
	public ResponseEntity<Void> removeEmployeeById(@PathVariable String employeeId, @PathVariable String skillid) {
		if(!EmployeeSkillUtil.isValidId(employeeId)) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if(!EmployeeSkillUtil.isValidId(skillid)) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		Skill skill = skillService.findSkillById(skillid);

		Employee theEmployee = employeeService.findEmployeeById(employeeId);
		if (null != theEmployee) {

			if (theEmployee.getSkill().contains(skill)) {
				theEmployee.getSkill().remove(skill);
				employeeService.updateEmployeeById(theEmployee);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {

				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

			}

		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}
}
