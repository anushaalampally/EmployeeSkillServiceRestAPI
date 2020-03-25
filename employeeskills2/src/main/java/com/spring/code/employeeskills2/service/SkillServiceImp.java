
package com.spring.code.employeeskills2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.code.employeeskills2.dao.SkillRepository;
import com.spring.code.employeeskills2.entity.Employee;
import com.spring.code.employeeskills2.entity.Skill;

@Service
public class SkillServiceImp implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public List<Skill> findAll() { // TODO Auto-generated method stub
		return skillRepository.findAll();
	}

	@Override
	public Skill save(Skill theSkill) { // TODO Auto-generated method
		return skillRepository.save(theSkill);
		
	}

	@Override
	public Skill findSkillById(String skillid) {
		// TODO Auto-generated method stub
		Optional<Skill> result = skillRepository.findById(skillid);
		Skill skill = null;
		if (result.isPresent()) {
			skill = result.get();
		}
		// TODO Auto-generated method stub
		return skill;
	}

}
