package com.encore.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.encore.dao.EmployeeDAO;
import com.encore.vo.Employee;

import config.ServerInfo;

public class EmployeeDAOImpl implements EmployeeDAO {

	// 싱글톤 구현
	private static EmployeeDAOImpl service = new EmployeeDAOImpl();

	private EmployeeDAOImpl() {
		// 1. 생성자를 통한 Driver Loading
		try {
			Class.forName(ServerInfo.DRIVER);
			System.out.println("Driver Loading..");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static EmployeeDAOImpl getInstance() {
		return service;
	}

	// 메소드 구현
	@Override
	public Connection getConnect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
			System.out.println("DB Server Connecting..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertEmp(Employee emp) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = getConnect();

			String query = "INSERT INTO employee VALUES(?,?,?,?)";

			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement Creating..");

			ps.setInt(1, emp.getNum());
			ps.setString(2, emp.getName());
			ps.setDouble(3, emp.getSalary());
			ps.setString(4, emp.getAddress());

			ps.executeUpdate();

		} catch (SQLException e) {

		} finally {
			closeAll(ps, conn);
		}

	}

	@Override
	public void removeEmp(int num) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();

			String query = "DELETE FROM employee WHERE num=?";

			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement Creating..");

			ps.setInt(1, num);

			ps.executeUpdate();

		} catch (SQLException e) {

		} finally {
			closeAll(ps, conn);
		}

	}

	@Override
	public void updateEmp(Employee emp) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			String query = "UPDATE employee SET name=?, salary=?, address=? where num=?";

			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement Creating..");

			ps.setString(1, emp.getName());
			ps.setDouble(2, emp.getSalary());
			ps.setString(3, emp.getAddress());
			ps.setInt(4, emp.getNum());

			ps.executeUpdate();

		} catch (SQLException e) {

		} finally {
			closeAll(ps, conn);
		}

	}

	@Override
	public ArrayList<Employee> selectAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Employee> temp = new ArrayList<Employee>();

		try {
			conn = getConnect();
			String query = "SELECT num,name,salary,address FROM employee";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement Creating..");

			rs = ps.executeQuery();

			while (rs.next()) {
				temp.add(new Employee(rs.getInt("num"), rs.getString("name"), rs.getDouble("salary"),
						rs.getString("address")));
			}
		} catch (SQLException e) {

		} finally {
			closeAll(rs, ps, conn);
		}

		return temp;
	}

}
