package jdbc.step1.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 디비 Access 하기위한 4단계 작업을 작성하는 로직
 * 1. DB서버에 대한 파편적인 정보들을 상수로 지정
 *  - 실제 값 : 드라이버명, DB서버정보 등
 */

public class JDBC4StepTest {
	// 1. Field
	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static String URL = "jdbc:mysql://127.0.0.1:3306/scott?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
	public static String USER = "root";
	public static String PASSWORD = "1234";

	public JDBC4StepTest() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		System.out.println("1. Driver Loading Success!");

		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("2. DB Connection Success!");

//		String query = "INSERT INTO mytable(num,name,address) VALUES(?,?,?)";
//		PreparedStatement ps = conn.prepareStatement(query);
//		System.out.println("3. PreparedStatement Creating Success!");
//
//		ps.setInt(1, 111);
//		ps.setString(2, "아이유");
//		ps.setString(3, "여의도");
//		ps.executeUpdate();

//		int row = ps.executeUpdate();
//		System.out.println(row + "row insert...ok");

//		String querydel = "DELETE FROM mytable WHERE num=?";
//		PreparedStatement ps1 = conn.prepareStatement(querydel);
//		System.out.println("3. PreparedStatement Creating Success!");
//
//		ps1.setInt(1, 444);
//		ps1.executeUpdate();

//		String queryUpdate = "UPDATE mytable set name=?, address=? where num=?";
//		PreparedStatement ps2 = conn.prepareStatement(queryUpdate);
//		System.out.println("3. PreparedStatement Creating Success!");
//
//		ps2.setString(1, "정우재");
//		ps2.setString(2, "방배동");
//		ps2.setInt(3, 111);
//		
//		System.out.println(ps2.executeUpdate() + "row update...ok");

		String querySelect = "SELECT num, name, address FROM mytable";
		PreparedStatement ps3 = conn.prepareStatement(querySelect);

		ResultSet rs = ps3.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getInt("num") + ", " + rs.getString("name") + ", " + rs.getString("address"));
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new JDBC4StepTest();
	}

}
