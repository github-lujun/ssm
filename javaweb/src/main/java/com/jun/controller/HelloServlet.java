package com.jun.controller;

import com.jun.bean.Account;
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
import java.util.List;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello,I'm HelloServlet.");
        //todo:Servlet相关知识点
        String method = req.getParameter("method");
        //todo:路由
        if(method!=null&&method.equals("selectAll")){
            List<Account> accounts = selectAll();
            resp.setStatus(200);
            resp.getWriter().println(accounts);
        }else {
            resp.setStatus(404);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    public List<Account> selectAll(){
        //todo:JDBC相关知识点
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            //connection = ServletContextInitConfig.getConnection();
            connection = ServletContextInitConfig.getCP30Connection();
            //todo:事务
            statement = connection.createStatement();

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
            //System.out.println(accounts);
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
        return accounts;
    }
}
