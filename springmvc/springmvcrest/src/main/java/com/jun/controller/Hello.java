package com.jun.controller;

import com.jun.pojo.Comer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Hello {

	@RequestMapping("/hello/{year}")
	public String say(@PathVariable("year") int year, @RequestParam(name = "month") int month) {
		System.out.println("this is "+this.getClass().getName());
		System.out.println("year: "+year+", month: "+month);
		return year+"-"+month;
	}

	@RequestMapping("/come")
	public Comer come(@RequestBody Comer comer){
		System.out.println(comer);
		return comer;
	}

	@RequestMapping("/comejson")
	public Comer come(){
		RestTemplate restTemplate = new RestTemplate();
		Comer request = new Comer();
		request.setName("lujun");
		request.setAge(22);
		Comer comer = restTemplate.postForObject("http://localhost:8080/springmvcrestprovider-1.0-SNAPSHOT/come", request,Comer.class);
		return comer;
	}
}
