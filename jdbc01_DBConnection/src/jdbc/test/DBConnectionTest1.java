package jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 1. 서버와 연결하기 위해서 먼저 Driver를 메모리에 로딩
 * 2. DB 서버와 연결
 *  -> 연결에 성공하면 Connection객체가 반환
 * 3. PreparedStatement 생성
 */

public class DBConnectionTest1 {
	public DBConnectionTest1() {
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loading Success!!");

			// 2. DBServer 연결
			String url = "jdbc:mysql://127.0.0.1:3306/scott?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("DB Server Connection..OK");

			// 3. PreparedStatement 생성
			String sql = "SELECT * FROM myscott";
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("prepareStatement..Creating");

			// 4. SQL문 실행...executeQuery();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1) + "," + rs.getInt(2));
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Driver Loading Fail..!");
		} catch (SQLException e) {
			System.err.println("DB Server Connection.. Fail..!");
		}
	}

	public static void main(String[] args) {
		new DBConnectionTest1();
	}

}
