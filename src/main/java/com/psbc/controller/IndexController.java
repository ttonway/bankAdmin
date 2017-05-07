package com.psbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/")
public class IndexController {
	private static Map<String, Double> jobmap = new HashMap<String, Double>();
	static {
		jobmap.put("select4A", 0.55);
		jobmap.put("select4B", 0.75);
		jobmap.put("select4C", 0.85);
	}



	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "/index";
	}	

}
