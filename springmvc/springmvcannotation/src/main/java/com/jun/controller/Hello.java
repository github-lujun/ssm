package com.jun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	@RequestMapping("/hello")
	public String say() {
		System.out.println("this is "+this.getClass().getName());
		return "good";
	}
}
