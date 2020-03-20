package com.jun.dao.impl;

import com.jun.dao.PeopleDao;
import com.jun.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class PeopleDaoImpl implements PeopleDao {
    @Override
    public List<Person> getPeoples() {
        return new ArrayList<>();
    }
}
