package com.jun.config;

import com.jun.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public Person person(){
        return new Person();
    }
}
