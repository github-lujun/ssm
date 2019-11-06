package com.jun.dao.impl;

import com.jun.listener.ServletContextInitConfig;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T> {
    public List<T> selectAll(String sql,Class<T> clazz) {
        //todo:重构抽出公共逻辑-----mybatis------
        List<T> list = new ArrayList<>();
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            //connection = ServletContextInitConfig.getConnection();
            connection = ServletContextInitConfig.getCP30Connection();

            /*=======================================================*/
            //statement = connection.createStatement();
            statement = connection.prepareStatement(sql);
            //rs = statement.executeQuery(sql);//查询
            rs = statement.executeQuery();//预处理查询使用无参方法
            while (rs.next()){
                T t = clazz.newInstance();
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for(Method method:declaredMethods){
                    if(method.getName().startsWith("set")){
                        String[] gets = method.getName().split("get");
                        method.invoke(t,rs.getString(gets[1].toLowerCase()));
                    }
                }
                list.add(t);
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

        return list;
    }
}
