package com.koreait.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/jcp";
			String uid = "root";
			String upw = "1234";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, uid, upw);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
