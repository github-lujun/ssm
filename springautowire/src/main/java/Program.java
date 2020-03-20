import com.jun.Config;
import com.jun.pojo.Person;
import com.jun.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ComponentScan or <context:component-scan base-package="com.jun"></context:component-scan>
 * @Component
 * @Autowire
 * */
public class Program {
    public static void main(String[] args) {
        ApplicationContext applicationContext = null;
        applicationContext = new AnnotationConfigApplicationContext(Config.class);
        //applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonService personService = applicationContext.getBean(PersonService.class);
        Person person = personService.getPerson(1);
        System.out.println(person);
    }
}
