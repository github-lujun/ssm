package com.jun.controller;

import com.jun.pojo.Comer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hello {
	@RequestMapping("/hello/{year}")
	public String say(Model model, @PathVariable("year") int year, @RequestParam(name = "month") int month) {
		model.addAttribute("school","ccdx");
		System.out.println("this is "+this.getClass().getName());
		System.out.println("year: "+year+", month: "+month);
		return "good";
	}

	@RequestMapping("/come")
	public String come(Comer comer){
		System.out.println(comer);
		return "good";
	}
}
