package com.jun;

import com.jun.mapper.StudentMapper;
import com.jun.pojo.Person;
import com.jun.pojo.Student;
import com.jun.service.PeopleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SpringBootApplication
/*@Configuration
@ComponentScan
@EnableAutoConfiguration*/
/*@MapperScan("com.jun.mapper")*/
public class Program implements CommandLineRunner {
    @Autowired
    private ApplicationContext appContext;

    @Value("${myname}")
    private String myName;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private StudentMapper studentMapper;

    public static void main(String args[]){
        SpringApplication.run(Program.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*String[] beanDefinitionNames = appContext.getBeanDefinitionNames();
        for (String beanName :
                beanDefinitionNames) {
            System.out.println(beanName);
        }*/

        /*System.out.println(this.myName);*/

        List<Student> peole = this.peopleService.getPeole();
        System.out.println(peole);

        //System.out.println(this.studentMapper==null);
    }
}
