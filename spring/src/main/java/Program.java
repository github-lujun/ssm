import com.jun.pojo.Book;
import com.jun.pojo.Person;
import com.jun.service.PeopleService;
import com.jun.service.impl.PeopleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String args[]){
        System.out.println(new Date());
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
        /*ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PeopleService peopleService = context.getBean("peopleImpl", PeopleService.class);
        List<Person> peole = peopleService.getPeole();
        System.out.println(peole);*/
    }
}
