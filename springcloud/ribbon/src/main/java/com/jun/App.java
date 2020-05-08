package com.jun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RequestMapping("/ribbon")
@RestController
@EnableEurekaClient
@SpringBootApplication
public class App {
    @Autowired
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    public static void main(String[] arg){
        SpringApplication.run(App.class,arg);
    }

    @RequestMapping("/hello")
    public String hello(){
        String s = this.restTemplate.getForObject("http://eureka.provider/", String.class, new HashMap<>());
        return s;
    }
}
