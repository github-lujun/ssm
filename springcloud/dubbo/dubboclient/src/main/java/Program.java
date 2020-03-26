import com.jun.IPerson;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * http://dubbo.apache.org/zh-cn/docs/user/quick-start.html
 * */
public class Program {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        IPerson person = context.getBean("personService", IPerson.class);
        String name = person.getName();
        System.out.println(name);
    }
}
