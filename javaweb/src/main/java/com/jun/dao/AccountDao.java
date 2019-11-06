package com.jun.dao;

import com.jun.bean.Account;

import java.util.List;

public interface AccountDao {
    public List<Account> selectAll();
    public boolean add(Account account);
}
