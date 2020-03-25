
  package com.spring.code.employeeskills2.controller;
  
  import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import
  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RestController;

import com.spring.code.employeeskills2.entity.Employee;
import com.spring.code.employeeskills2.entity.Skill; import
  com.spring.code.employeeskills2.service.SkillService;
  
  @RestController
  
  @RequestMapping("/skillapi") public class SkillRestController {
  
  @Autowired private SkillService skillService;
  
  @RequestMapping("/hello") public String hello() { return "Anusha is good"; }
  
  @GetMapping("/skills")
  public List<Skill> findAll() { 
	  return  skillService.findAll();
	  }
  
  @PostMapping("/skills") 
  public Skill addSkill(@RequestBody Skill theSkill)
  {
  
	  Skill addedSkill= skillService.save(theSkill);
  
  return addedSkill;
  }
  @GetMapping("/skills/{skillId}")
	public Skill findSkillById(@PathVariable String skillId) {
		
		
		
		Skill skill = skillService.findSkillById(skillId);
		
		return skill;
  
  
  }
  }
 