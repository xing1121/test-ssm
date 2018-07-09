package com.sf.ssm.service;

import java.util.List;

import com.sf.ssm.pojo.Person;

public interface PersonService {

	/**
	 * 插入一条数据
	 *	@ReturnType	void 
	 *	@Date	2018年6月20日	下午6:20:24
	 *  @Param  @param person
	 */
	public void insert(Person person);
	
	/**
	 * 根据主键选择性字段更新（只更新非空字段）
	 *	@ReturnType	void 
	 *	@Date	2018年6月20日	下午6:20:28
	 *  @Param  @param person
	 */
	public void updateById(Person person);
	
	/**
	 * 根据主键删除
	 *	@ReturnType	void 
	 *	@Date	2018年6月20日	下午6:20:35
	 *  @Param  @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * 根据主键查询一条数据
	 *	@ReturnType	Person 
	 *	@Date	2018年6月20日	下午6:20:55
	 *  @Param  @param id
	 *  @Param  @return
	 */
	public Person queryById(Long id);
	
	/**
	 * 查询所有
	 *	@ReturnType	List<Person> 
	 *	@Date	2018年6月20日	下午6:21:04
	 *  @Param  @return
	 */
	public List<Person> queryAll();
	
}
