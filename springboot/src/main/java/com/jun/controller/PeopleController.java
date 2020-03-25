package com.jun.controller;

import com.jun.pojo.Student;
import com.jun.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @RequestMapping("getPeople")
    public void getPeole(){
        List<Student> peole = this.peopleService.getPeole();
        System.out.println(peole);
    }
}
