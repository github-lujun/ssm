package com.jun.service.impl;

import com.jun.mapper.StudentMapper;
import com.jun.pojo.Student;
import com.jun.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleImpl implements PeopleService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getPeole() {
        return this.studentMapper.selectAll();
    }
}
