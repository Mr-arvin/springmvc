package jdbc;

import jdbc.bean.User;
import org.junit.Test;

import java.sql.*;
import java.util.*;

/**
 * 使用jdbc实现增删改
 */

public class CRUD_Test01 {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName ("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/JDBC_Study","root","asdasdasd");

        Statement stmt = conn.createStatement ();

        int flag = stmt.executeUpdate ("INSERT INTO t_user(name,password,email,birthday) VALUES\n" +
                "('cat','24323423','cat@163.com','1999-11-11')");

        if(flag > 0){
            System.out.println ("成功");
        }

        stmt.close ();
        conn.close ();
    }

    @Test
    public void testUpdata() throws ClassNotFoundException, SQLException {
        Class.forName ("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/JDBC_Study","root","asdasdasd");

        Statement stmt = conn.createStatement ();

        int flag = stmt.executeUpdate ("update `JDBC_Study`.`t_user` set `password`='qweqweqwe', `id`='4', `email`='cat@163.com', `name`='cat', `birthday`='1999-11-11' where `id`='4';");

        if(flag > 0){
            System.out.println ("成功");
        }

        stmt.close ();
        conn.close ();
    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName ("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/JDBC_Study","root","asdasdasd");

        Statement stmt = conn.createStatement ();

        int flag = stmt.executeUpdate ("DELETE FROM t_user WHERE name='tiger'");

        if(flag > 0){
            System.out.println ("成功");
        }

        stmt.close ();
        conn.close ();
    }

    //正确的写法
    @Test
    public void testSelect()  {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //注册驱动
        try {
            Class.forName ("com.mysql.jdbc.Driver");
            //获取连接Connection
            conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/JDBC_Study", "root", "asdasdasd");
            //得到执行sequel语句的对象Statement
            stmt = conn.createStatement ();
            //执行sql语句，并返回结果
            rs = stmt.executeQuery ("select id,name,password,email,birthday from t_user");

            //处理结果
            List<User> userList = new ArrayList<>();
            while(rs.next()){
                User u = new User ();
                u.setId (rs.getInt ("id"));
                u.setName (rs.getString ("name"));
                u.setPassword (rs.getString ("password"));
                u.setEmail (rs.getString ("email"));
                u.setBirthday (rs.getDate ("birthday"));
                userList.add (u);
            }

            System.out.println (userList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally { //finally 保证会执行到这里并执行关闭
            //关闭资源
            try {
                if(rs != null){
                    rs.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }

    }
}