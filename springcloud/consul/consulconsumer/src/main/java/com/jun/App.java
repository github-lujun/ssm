package com.jun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate loadbalancedRestTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String hello(){
        String s = this.restTemplate.getForObject("http://consulprovider/", String.class, new HashMap<>());

        //String s = this.restTemplate.getForObject("http://Leo.lan:8508/", String.class, new HashMap<>());

        /*ServiceInstance serviceInstance = this.loadBalancerClient.choose("consulprovider");
        String s = this.restTemplate.getForObject(serviceInstance.getUri()+"/", String.class, new HashMap<>());*/

        /*List<ServiceInstance> instances = this.discoveryClient.getInstances("consulprovider");
        instances.forEach(i->{
            System.out.println(i.getServiceId());
            String s = this.restTemplate.getForObject(i.getUri()+"/", String.class, new HashMap<>());
            System.out.println(s);
        });*/

        return s;
    }
}
