package com.jun.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("EUREKA.PROVIDER")
public interface HelloService {

    @RequestMapping("/")
    String hello();
}

