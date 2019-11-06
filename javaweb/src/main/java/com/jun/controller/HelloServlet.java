package com.jun.controller;

import com.jun.bean.Account;
import com.jun.listener.ServletContextInitConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello,I'm HelloServlet.");
        //todo:Servlet相关知识点
        String method = req.getParameter("method");
        //todo:路由
        //todo:JDBC相关知识点
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
            boolean add = Add(account);
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

        List<Account> accounts = new ArrayList<>();

        //todo:重构抽出公共逻辑
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            //connection = ServletContextInitConfig.getConnection();
            connection = ServletContextInitConfig.getCP30Connection();
            statement = connection.createStatement();

            /*=======================================================*/
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
            /*=======================================================*/

            //System.out.println(accounts);
        }catch (Exception ex){
            ex.printStackTrace();
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

    public boolean Add(Account account){

        Connection connection = null;
        //Statement statement = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            //connection = ServletContextInitConfig.getConnection();
            connection = ServletContextInitConfig.getCP30Connection();
            //事务
            connection.setAutoCommit(false);//不自动commit
            /*=======================================================*/
            //String sql="insert into account(userName,password) values('"+account.getUserName()+"','"+account.getPassword()+"')";
            String sql="insert into account(userName,password) values(?,?)";
            //statement = connection.createStatement();
            //connection.prepareCall();//todo:??
            statement = connection.prepareStatement(sql);//todo:预处理语句
            statement.setString(1,account.getUserName());
            statement.setString(2,account.getPassword());
            boolean execute = statement.execute(sql);
            /*=======================================================*/

            connection.commit();//提交
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                connection.rollback();//回滚
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
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
}
