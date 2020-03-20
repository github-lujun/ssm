package com.jun.service.impl;

import com.jun.dao.PeopleDao;
import com.jun.pojo.Person;
import com.jun.service.PeopleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class PeopleServiceImpl implements PeopleService {
    private PeopleDao peopleDao;

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    public List<Person> getPeole() {
        return this.peopleDao.getPeoples();
    }
}
