package com.jun.controller;

import com.jun.Account;
import com.jun.listener.ServletContextInitConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello,I'm HelloServlet.");
        //todo:Servlet相关知识点

        //todo:JDBC相关知识点
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            connection = ServletContextInitConfig.getConnection();
            statement = connection.createStatement();
            ArrayList<Account> accounts = new ArrayList<>();
            String sql="select userName,password from account";
            rs = statement.executeQuery(sql);//查询
            while (rs.next()){
                Account account = new Account();
                String userName = rs.getString("userName");
                account.setUserName(userName);
                String password = rs.getString("password");
                account.setPassword(password);
                accounts.add(account);
            }
            System.out.println(accounts);
            //connection.commit();//提交
        }catch (Exception ex){
            ex.printStackTrace();
            /*try {
                connection.rollback();//回滚
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    if(!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    try {
                        connection.close();
                    } catch (SQLException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
