package com.runoob.test;
/**
 * 
 * @author liangyao
 * @date 2019年4月10日
 */
//ResultSet接口实现查询操作：
//步骤如下：
//1、加载数据库驱动程序：Class.forName(驱动程序类)
//2、通过用户名密码和连接地址获取数据库连接对象：DriverManager.getConnection(连接地址,用户名,密码)
//3、构造查询SQL语句
//4、创建Statement实例：Statement stmt = conn.createStatement()
//5、执行查询SQL语句，并返回结果：ResultSet rs = stmt.executeQuery(sql)
//6、处理结果
//7、关闭连接：rs.close()、stmt.close()、conn.close()
import java.sql.*;
public class MySQLDemo {
	// JDBC 驱动名及数据库 URL
	//现在按照最新官方提示支持将com.mysql.jdbc.Driver  改为  com.mysql.cj.jdbc.Driver
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    //useUnicode=true&characterEncoding=utf8&serverTimezone=GMT注意时区问题
    static final String DB_URL = "jdbc:mysql://localhost:3306/STUDENT?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Connection conn = null;
		Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            //对数据库的查询操作，一般需要返回查询结果，在程序中，JDBC为我们提供了ResultSet接口来专门处理查询结果集。
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}

