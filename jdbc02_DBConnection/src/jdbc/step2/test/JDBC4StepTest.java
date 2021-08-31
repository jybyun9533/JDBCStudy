package jdbc.step2.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import config.ServerInfo;

/*
 * 디비 Access 하기위한 4단계 작업을 작성하는 로직
 * 1. DB서버에 대한 파편적인 정보들을 상수로 지정
 *  - 실제 값 : 드라이버명, DB서버정보 등
 *  
 *  ### 문제점 ###
 *  소스코드에 서버정보가 그대로 노출되어져 있다.
 *   -> 메타데이터화 시키자
 *  ### 해결책 ###
 *  
 *  2. 상수값과 추상메소드를 구성요소로 가지는 인터페이스를 별도의 모듈로 생성
 *     그 안에 서버 정보를 메타데이터화 시키기.
 *     
 *  3. 자바 진영에서 가장 많이 사용하는 Meta Data는 properties 파일이다.
 *     key-value값을 모두 스트링으로 저장할 수 있다는 특징에 착안되어 서버정보를 저장하는 메타데이터로 가장 많이 쓰인다.
 *     config < db.properties
 */

public class JDBC4StepTest implements ServerInfo {

	static String driver;
	static String url;
	static String user;
	static String pass;
	static String query;
	
	public JDBC4StepTest() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		System.out.println("1. Driver Loading Success!");

		Connection conn = DriverManager.getConnection(url, user, pass);
		System.out.println("2. DB Connection Success!");
		
		//String querySelect = "SELECT num, name, address FROM mytable";
		PreparedStatement ps3 = conn.prepareStatement(query);

		ResultSet rs = ps3.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getInt("num") + ", " + rs.getString("name") + ", " + rs.getString("address"));
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new JDBC4StepTest();
	}

	static {
		// 1. properties 파일의 내용을 로드해온다.
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("src/config/db.properties"));

			driver = p.getProperty("jdbc.mysql.driver");
			url = p.getProperty("jdbc.mysql.url");
			user = p.getProperty("jdbc.mysql.user");
			pass = p.getProperty("jdbc.mysql.pass");
			query = p.getProperty("jdbc.sql.selectAll");

		} catch (Exception e) {
		}

	}// static

}
