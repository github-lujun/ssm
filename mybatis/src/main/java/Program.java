import com.jun.mapper.StudentMapper;
import com.jun.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * */
public class Program {
    public static void main(String args[]) throws IOException {
        //System.out.println("hello");
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            //List<Student> selectAll = sqlSession.selectList("com.jun.mapper.StudentMapper.selectAll");
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            /*Student student = new Student();
            student.setId(16);
            student.setName("lujun");
            student.setAge(30);
            mapper.insert(student);

            sqlSession.commit();*/



            /*Student lujun = mapper.selectOne("demo");

            System.out.println(lujun);

            lujun.setName("demo");
            lujun.setAge(28);

            mapper.update(lujun);

            sqlSession.commit();*/

            /*mapper.delete(16);

            sqlSession.commit();*/

            /*Student student = new Student();

            student.setAge(20);
            student.setName("lujun");

            List<Student> select = mapper.select(student);

            for (Student s:select) {
                System.out.println(s);
            }*/

            /*ArrayList<Integer> integers = new ArrayList<Integer>();

            integers.add(20);
            integers.add(31);

            List<Student> students = mapper.selectIn(integers);

            students.forEach(o->{
                System.out.println(o);
            });*/

            /*List<Student> students = mapper.selectYearBlow(25);

            students.forEach(o->{
                System.out.println(o);
            });*/

            /*List<Student> o = mapper.selectLikeName("oj");

            o.forEach(a->{
                System.out.println(a);
            });*/

            /*List<Student> students = new ArrayList<>();

            Student student1 = new Student();
            student1.setId(16);
            student1.setName("16");
            student1.setAge(16);

            Student student2 = new Student();
            student2.setId(17);
            student2.setName("17");
            student2.setAge(17);

            students.add(student1);
            students.add(student2);

            mapper.insertList(students);

            sqlSession.commit();*/

            /*ArrayList<Integer> integers = new ArrayList<>();
            integers.add(16);
            integers.add(17);
            mapper.deleteListById(integers);
            sqlSession.commit();*/

            List<Student> selectAll = mapper.selectAll();
            for (Student s : selectAll) {
                System.out.println(s);
            }
        }
    }
}
