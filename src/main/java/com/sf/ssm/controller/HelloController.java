package com.sf.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：最简单的HelloWorld
 * @author 80002888
 * @date   2018年7月9日
 */
@Controller
public class HelloController {

	@RequestMapping(value="hello")
	public String hello(){
		return "success";
	}
	
}
