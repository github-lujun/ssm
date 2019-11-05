package com.jun.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServletContextInitConfig implements ServletContextListener {

    private static String url;
    private static String username;
    private static String password;
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.err.println("数据库连接成功, url:"+url+",username:"+username+",password:"+password);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("数据库连接失败, url:"+url+",username:"+username+",password:"+password);
            return null;
        }
        return connection;
    }

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

        url = sce.getServletContext().getInitParameter("jdbc.url");
        username = sce.getServletContext().getInitParameter("jdbc.username");
        password = sce.getServletContext().getInitParameter("jdbc.password");
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
