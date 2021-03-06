package com.jun.dao.impl;

import com.jun.bean.Account;
import com.jun.dao.AccountDao;
import com.jun.listener.ServletContextInitConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * todo:JDBC相关知识点
 * 预处理语句+数据库连接池+事务
 */
public class AccountDaoImpl extends BaseDao implements AccountDao {
    /**
     *预处理语句+数据库连接池
     * @return
     */
    @Override
    public List<Account> selectAll() {
        List<Account> accounts = new ArrayList<>();
        String sql="select userName,password from account";
        List list = selectAll(sql, Account.class);
        return list;
    }

    /**
     *事务
     * @param account
     * @return
     */
    @Override
    public boolean add(Account account) {
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
            //connection.prepareCall();//todo:调用存储过程
            statement = connection.prepareStatement(sql);//预处理语句
            statement.setString(1,account.getUserName());
            statement.setString(2,account.getPassword());
            int i = statement.executeUpdate();//!!注意:此处使用无参数的方法!!
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
