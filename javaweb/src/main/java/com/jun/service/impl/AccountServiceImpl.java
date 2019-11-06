package com.jun.service.impl;

import com.jun.bean.Account;
import com.jun.dao.AccountDao;
import com.jun.dao.impl.AccountDaoImpl;
import com.jun.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    public AccountServiceImpl(){
        accountDao = new AccountDaoImpl();
    }

    @Override
    public List<Account> selectAll() {
        return this.accountDao.selectAll();
    }

    @Override
    public boolean add(Account account) {
        return this.accountDao.add(account);
    }
}
