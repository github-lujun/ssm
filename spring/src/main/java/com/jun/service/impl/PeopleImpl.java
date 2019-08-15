package com.jun.service.impl;

import com.jun.pojo.Person;
import com.jun.service.PeopleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleImpl implements PeopleService {
    @Override
    public List<Person> getPeole() {
        return new ArrayList<>();
    }
}
