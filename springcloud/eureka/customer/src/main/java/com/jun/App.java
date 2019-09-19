package com.jun;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class App {
    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        return this.restTemplate.getForObject("DESKTOP-LQ84CS8.lan:eureka.client:8762",String.class);
    }

    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
