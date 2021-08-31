package com.encore.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.encore.vo.Person;

import config.ServerInfo;

/*
 * DAO + Test
 * JDBC 4단계(드라이버 로딩은 제외)
 * 2. 디비연결
 * 3. 
 */
public class SimplePersonJDBCTest implements ServerInfo {

	public SimplePersonJDBCTest() throws ClassNotFoundException {
		Class.forName(ServerInfo.DRIVER);
		System.out.println("Driver Loading..");
	}

	// 고정적으로 반복되는 부분을 공통적인 로직으로 정의
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		System.out.println("DB Server Connecting..");
		return conn;
	}

	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}

	// 비지니스 로직(DB Access하는 로직) 정의
	public void addPerson(Person p) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		conn = getConnect();

		String query = "INSERT INTO person VALUES(?,?,?)";

		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement Creating..");

		ps.setInt(1, p.getSsn());
		ps.setString(2, p.getName());
		ps.setString(3, p.getAddress());

		System.out.println(ps.executeUpdate() + "명 추가되었습니다.");

		closeAll(ps, conn);
	}

	public void deletePerson(int ssn) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		conn = getConnect();

		String query = "DELETE FROM person WHERE ssn=?";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement Creating..");

		ps.setInt(1, ssn);
		System.out.println(ps.executeUpdate() + "명 삭제되었습니다.");

		closeAll(ps, conn);
	}

	public void updatePerson(Person p) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		conn = getConnect();

		String query = "UPDATE person set name=?, address=? where ssn=?";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement Creating..");

		ps.setString(1, p.getName());
		ps.setString(2, p.getAddress());
		ps.setInt(3, p.getSsn());

		System.out.println(ps.executeUpdate() + "명 변경되었습니다.");
	}

	public ArrayList<Person> findAllPerson() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Person> list = new ArrayList<Person>();
		
		conn = getConnect();

		String query = "select ssn, name, address from person";
		ps = conn.prepareStatement(query);
		System.out.println("PreparedStatement Creating..");

		rs = ps.executeQuery();

		while (rs.next()) {
			list.add(new Person(rs.getInt("ssn"), rs.getString("name"), rs.getString("address")));
		}

		return list;
	}

	public static void main(String[] args) throws Exception {
		SimplePersonJDBCTest jdbc = new SimplePersonJDBCTest();
		// jdbc.addPerson(new Person(222, "Jane", "Texas"));
		jdbc.updatePerson(new Person(222, "Jane", "NY"));
		System.out.println(jdbc.findAllPerson());

	}

}
