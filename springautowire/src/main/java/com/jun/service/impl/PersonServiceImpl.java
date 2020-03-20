package com.jun.service.impl;

import com.jun.dao.PersonDao;
import com.jun.pojo.Person;
import com.jun.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public Person getPerson(Integer id) {
        return this.personDao.getPerson(id);
    }
}
