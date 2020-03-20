package com.jun.dao.impl;

import com.jun.dao.PersonDao;
import com.jun.pojo.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDaoImpl implements PersonDao {
    @Override
    public Person getPerson(Integer id) {
        return new Person();
    }
}
