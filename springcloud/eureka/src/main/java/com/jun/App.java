package com.jun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class App {
    @RequestMapping("/")
    public String home(){
        return "Hello world";
    }

    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
