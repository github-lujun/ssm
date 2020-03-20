package com.jun;

import com.jun.config.SpringConfig;
import com.jun.pojo.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Configuration
 * @Bean
 * */
public class Program {
    public static void main(String args[]){
        System.out.println("hello");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }
}
