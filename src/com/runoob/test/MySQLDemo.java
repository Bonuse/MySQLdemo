package com.runoob.test;
/**
 * 
 * @author liangyao
 * @date 2019��4��10��
 */
//ResultSet�ӿ�ʵ�ֲ�ѯ������
//�������£�
//1���������ݿ���������Class.forName(����������)
//2��ͨ���û�����������ӵ�ַ��ȡ���ݿ����Ӷ���DriverManager.getConnection(���ӵ�ַ,�û���,����)
//3�������ѯSQL���
//4������Statementʵ����Statement stmt = conn.createStatement()
//5��ִ�в�ѯSQL��䣬�����ؽ����ResultSet rs = stmt.executeQuery(sql)
//6��������
//7���ر����ӣ�rs.close()��stmt.close()��conn.close()
import java.sql.*;
public class MySQLDemo {
	// JDBC �����������ݿ� URL
	//���ڰ������¹ٷ���ʾ֧�ֽ�com.mysql.jdbc.Driver  ��Ϊ  com.mysql.cj.jdbc.Driver
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    //useUnicode=true&characterEncoding=utf8&serverTimezone=GMTע��ʱ������
    static final String DB_URL = "jdbc:mysql://localhost:3306/STUDENT?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
 
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "123456";
    
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Connection conn = null;
		Statement stmt = null;
        try{
            // ע�� JDBC ����
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            //�����ݿ�Ĳ�ѯ������һ����Ҫ���ز�ѯ������ڳ����У�JDBCΪ�����ṩ��ResultSet�ӿ���ר�Ŵ����ѯ�������
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // �������
                System.out.print("ID: " + id);
                System.out.print(", վ������: " + name);
                System.out.print(", վ�� URL: " + url);
                System.out.print("\n");
            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}

