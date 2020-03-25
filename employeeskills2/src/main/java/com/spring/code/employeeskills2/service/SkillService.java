
  package com.spring.code.employeeskills2.service;
  
  import java.util.List;
  
  
  import com.spring.code.employeeskills2.entity.Skill;
  
  public interface SkillService {
	  public List<Skill> findAll(); 
	  public Skill save(Skill theSkill); 
	  public Skill findSkillById(String skillid);
	  }
 