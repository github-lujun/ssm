package com.jun.dao.impl;

import com.jun.dao.HelloDao;
import com.jun.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class HelloDaoImpl implements HelloDao {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Account get(String username) {
        Account account = jdbcOperations.queryForObject("select * from account where userName='" + username + "' limit 1", new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account acc = new Account();
                acc.setUsername(resultSet.getString("userName"));
                acc.setPassword(resultSet.getString("password"));
                return acc;
            }
        });
        return account;
    }
}
