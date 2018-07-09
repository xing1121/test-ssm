package com.sf.ssm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.ssm.pojo.Person;
import com.sf.ssm.service.PersonService;

/**
 * 描述：拦截所有/person请求
 * @author 80002888
 * @date   2018年7月9日
 */
@Controller
@RequestMapping(value="/person")
public class PersonController {

	private Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;

	/**
	 * 拦截/person/queryAll请求，返回List<Person>的json格式
	 *	@ReturnType	List<Person> 
	 *	@Date	2018年7月9日	下午2:48:09
	 *  @Param  @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryAll")
	public List<Person> queryAll(){
		return personService.queryAll();
	}

	/**
	 * 拦截/person/queryById请求，返回字符串
	 *	@ReturnType	String 
	 *	@Date	2018年7月9日	下午2:48:41
	 *  @Param  @param id
	 *  @Param  @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryById", produces="text/html;charset=UTF-8")
	public Person queryById(@RequestParam(name="id") Long id){
		return personService.queryById(id);
	}
	
	/**
	 * 拦截/person/deleteById请求，返回字符串
	 *	@ReturnType	String 
	 *	@Date	2018年7月9日	下午2:48:41
	 *  @Param  @param id
	 *  @Param  @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteById", produces="text/html;charset=UTF-8")
	public String deleteById(@RequestParam(name="id") Long id){
		try {
			personService.deleteById(id);
		} catch (Exception e) {
			logger.error("get error->" + e.getMessage(), e);
			return "服务器发生异常，" + e.getMessage() + "删除失败！";
		}
		return "删除成功！";
	}
	
	/**
	 * 拦截/person/insert请求，只接受post请求，返回字符串
	 *	@ReturnType	String 
	 *	@Date	2018年7月9日	下午2:51:43
	 *  @Param  @param person
	 *  @Param  @return
	 */
	@ResponseBody
	@RequestMapping(value="insert", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String insert(Person person){
		try {
			personService.insert(person);
		} catch (Exception e) {
			logger.error("get error->" + e.getMessage(), e);
			return "服务器发生异常，" + e.getMessage() + "添加失败！";
		}
		return "添加成功！";
	}
	
	/**
	 * 拦截/person/updateById请求，返回字符串
	 *	@ReturnType	String 
	 *	@Date	2018年7月9日	下午2:51:43
	 *  @Param  @param person
	 *  @Param  @return
	 */
	@ResponseBody
	@RequestMapping(value="updateById", produces="text/html;charset=UTF-8")
	public String updateById(Person person){
		try {
			personService.updateById(person);
		} catch (Exception e) {
			logger.error("get error->" + e.getMessage(), e);
			return "服务器发生异常，" + e.getMessage() + "更新失败！";
		}
		return "更新成功！";
	}
}
