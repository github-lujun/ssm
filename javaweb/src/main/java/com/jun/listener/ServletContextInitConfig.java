package com.jun.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServletContextInitConfig implements ServletContextListener {

    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized......");

        String jdbc_driver = sce.getServletContext().getInitParameter("jdbc.driver");
        try {
            Class.forName(jdbc_driver);
        } catch (Exception e) {
            System.err.println("jdbc_driver:"+jdbc_driver+",加载失败");
            e.printStackTrace();
            return;
        }

        String url = sce.getServletContext().getInitParameter("jdbc.url");
        String username = sce.getServletContext().getInitParameter("jdbc.username");
        String password = sce.getServletContext().getInitParameter("jdbc.password");

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.err.println("数据库连接成功, url:"+url+",username:"+username+",password:"+password);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("数据库连接失败, url:"+url+",username:"+username+",password:"+password);
            return;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed.......");
        if(connection!=null){
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
