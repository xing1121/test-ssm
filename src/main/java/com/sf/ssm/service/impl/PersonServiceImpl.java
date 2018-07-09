package com.sf.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.ssm.dao.PersonMapper;
import com.sf.ssm.pojo.Person;
import com.sf.ssm.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public void insert(Person person){
		personMapper.insert(person);
	}
	
	@Override
	public void updateById(Person person){
		personMapper.updateByPrimaryKeySelective(person);
	}
	
	@Override
	public void deleteById(Long id){
		personMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Person queryById(Long id){
		return personMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Person> queryAll(){
		return personMapper.selectByExample(null);
	}
	
}
