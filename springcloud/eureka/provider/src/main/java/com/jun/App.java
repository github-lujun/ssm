package com.jun;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class App {

    @Autowired
    private EurekaClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/")
    public String home(){
        /*InstanceInfo nextServerFromEureka = this.discoveryClient.getNextServerFromEureka("", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();*/
        return "Hello world,"+this.applicationName+":"+this.serverPort;//+":"+homePageUrl;
    }
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
