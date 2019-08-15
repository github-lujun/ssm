package com.jun.mapper;

import com.jun.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    /*@Select("select * from student")*/
    List<Student> selectAll();
}
