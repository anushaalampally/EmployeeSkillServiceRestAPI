
  package com.spring.code.employeeskills2.dao;
  
  import org.springframework.data.jpa.repository.JpaRepository;
  
  import com.spring.code.employeeskills2.entity.Employee; import
  com.spring.code.employeeskills2.entity.Skill;
  
  public interface SkillRepository extends JpaRepository<Skill, String>{
  
  }
 