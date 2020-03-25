package com.jun.service.impl;

import com.jun.controller.Hello;
import com.jun.dao.HelloDao;
import com.jun.pojo.Account;
import com.jun.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloDao helloDao;
    @Override
    public Account get(String username) {
        return this.helloDao.get(username);
    }
}
