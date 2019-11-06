package com.jun.service;

import com.jun.bean.Account;

import java.util.List;

public interface AccountService {
    public List<Account> selectAll();
    public boolean add(Account account);
}
