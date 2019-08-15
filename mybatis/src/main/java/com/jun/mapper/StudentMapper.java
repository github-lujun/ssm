package com.jun.mapper;

import com.jun.pojo.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    @Select("select * from student")
    List<Student> selectAll();
    int insert(Student student);
    Student selectOne(String name);
    int update(Student student);
    int delete(int id);
    List<Student> select(Student student);
    List<Student> selectIn(List<Integer> list);
    List<Student> selectYearBlow(int age);
    List<Student> selectLikeName(String likename);
    int insertList(List<Student> list);
    int deleteListById(List<Integer> list);
}
