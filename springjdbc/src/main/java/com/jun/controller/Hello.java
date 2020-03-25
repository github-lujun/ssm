package com.jun.controller;

import com.jun.pojo.Account;
import com.jun.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hello {
    @Autowired
    private HelloService helloService;

    @RequestMapping("person/{username}")
    public void person(@PathVariable("username") String username){
        Account person = this.helloService.get(username);
        System.out.println(person);
        return;
    }
}
