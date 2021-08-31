package jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * 디비 서버 정보에 해당하는 것들을 상수값으로 세팅
 * 1. 드라이버 FQCN --- 이 부분이 먼저 메모리에 로딩된 다음에 나머지 일들이 일어나야 한다.
 * 2. 서버주소, 계정이름, 비밀번호 등.
 */

public class DBConnectionTest2 {
	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static String URL = "jdbc:mysql://127.0.0.1:3306/scott?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
	public static String USER = "root";
	public static String PASSWORD = "1234";

	public DBConnectionTest2() {
		try {
			// 2. DB Server Connect
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Server Connection..Success!");

			// 3.
			String insertQuery = "INSERT INTO mytable(num,name,address) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			System.out.println("PreparedStatement Creating..");

			// 4. 값 바인딩 및 쿼리문 실행
			ps.setInt(1, 111);
			ps.setString(2, "박나래");
			ps.setString(3, "여의도");

			// int row = ps.executeUpdate(); // 업데이트된(추가된) row의 갯수 반환
			// System.out.println(row +"명 추가 되었습니다.");

			String deleteQuery = "DELETE FROM mytable WHERE name=?";
			PreparedStatement ps2 = con.prepareStatement(deleteQuery);
			ps2.setString(1, "박나래");
			System.out.println(ps2.executeUpdate()+"명 삭제되었습니다.");
		} catch (Exception e) {
			System.err.println("DB Connection Fail..");
		}
	}

	public static void main(String[] args) {
		new DBConnectionTest2();

	}

	static {
		try {
			// 1. Driver Loading
			Class.forName(DRIVER);
			System.out.println("Driver Loadin..Success!");
		} catch (ClassNotFoundException e) {

		}
	}

}
