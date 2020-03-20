package com.jun.service.impl;

import com.jun.dao.PersonDao;
import com.jun.pojo.Person;
import com.jun.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @Scope bean的作用域
 * */
@Component
/*@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)*/
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public Person getPerson(Integer id) {
        return this.personDao.getPerson(id);
    }
}
