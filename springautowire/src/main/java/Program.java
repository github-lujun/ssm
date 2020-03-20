import com.jun.Config;
import com.jun.pojo.Person;
import com.jun.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ComponentScan
 * @Component
 * @Autowire
 * */
public class Program {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        PersonService personService = applicationContext.getBean(PersonService.class);
        Person person = personService.getPerson(1);
        System.out.println(person);
    }
}
