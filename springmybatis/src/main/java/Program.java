import com.jun.mapper.StudentMapper;
import com.jun.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Program {
    public static void main(String args[]){
        ApplicationContext application = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        StudentMapper studentMapper = application.getBean("studentMapper", StudentMapper.class);
        List<Student> students = studentMapper.selectAll();
        students.forEach(student ->
        {
            System.out.println(student);
        });
    }
}
