import com.jun.pojo.Person;
import com.jun.service.PeopleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * applicationContext.xml
 * <bean></bean>
 * */
public class Program {
    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PeopleService peopleService = context.getBean("peopleService", PeopleService.class);
        List<Person> peole = peopleService.getPeole();
        System.out.println(peole);
    }
}
