package com.jun.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello(){
        String s = this.restTemplate.getForObject("http://EUREKA.PROVIDER2/", String.class, new HashMap<>());
        return s;
    }

    public String helloFallback(){
        return "error";
    }
}
