package com.jun.service;

//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "EUREKA.PROVIDER",fallback = HelloServiceFallback.class)
public interface HelloService {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    String hello();
}