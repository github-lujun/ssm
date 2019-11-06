package com.jun.controller;

import com.jun.bean.Account;
import com.jun.listener.ServletContextInitConfig;
import com.jun.service.AccountService;
import com.jun.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *todo:Servlet相关知识点
 */
public class HelloServlet extends HttpServlet {
    private AccountService accountService;

    public HelloServlet(){
        super();
        accountService = new AccountServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello,I'm HelloServlet.");

        String method = req.getParameter("method");
        //todo:路由
        if(method!=null&&method.equals("selectAll")){
            List<Account> accounts = selectAll();
            resp.setStatus(200);
            resp.getWriter().println(accounts);
        }else if(method!=null&&method.equals("add")){
            Account account = new Account();
            long[] chars = new long[8];
            for(int i=0;i<chars.length;i++){
                chars[i] = (int)(Math.random()*10);
            }
            account.setUserName(chars.toString());
            account.setPassword(chars.toString());
            boolean add = add(account);
            if(add){
                resp.setStatus(200);
            }else {
                resp.setStatus(500);
            }
        }else {
            resp.setStatus(404);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    public List<Account> selectAll(){
        return this.accountService.selectAll();
    }

    public boolean add(Account account){
        return this.accountService.add(account);
    }
}
