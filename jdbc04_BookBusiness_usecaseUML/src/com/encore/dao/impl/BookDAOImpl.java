package com.encore.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.encore.dao.BookDAO;
import com.encore.exception.BookNotFoundException;
import com.encore.exception.DuplicateISBNException;
import com.encore.exception.InvalidInputException;
import com.encore.vo.Book;

import config.ServerInfo;

public class BookDAOImpl implements BookDAO {

	// Singletone
	private static BookDAOImpl dao = new BookDAOImpl();

	private BookDAOImpl() {
		// 1. 생성자를 통한 Driver Loading
		try {
			Class.forName(ServerInfo.DRIVER);
			System.out.println("Driver Loading..");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static BookDAOImpl getInstance() {
		return dao;
	}

	// DB Connect

	public Connection getConnect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
			System.out.println("DB Server Connecting..");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public void closeAll(PreparedStatement ps, Connection conn) {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Method
	// 메소드 추가
	public boolean isIsbn(String isbn, Connection conn) throws SQLException {

		String query = "SELECT count(*) FROM book WHERE isbn = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	@Override
	public void registerBook(Book vo) throws SQLException, DuplicateISBNException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			if (!isIsbn(vo.getIsbn(), getConnect())) {
				String query = "INSERT INTO book VALUES(?,?,?,?,?)";
				ps = conn.prepareStatement(query);

				ps.setString(1, vo.getIsbn());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getWriter());
				ps.setString(4, vo.getPublisher());
				ps.setInt(5, vo.getPrice());

				ps.executeUpdate();
			} else {
				throw new DuplicateISBNException();
			}

		} finally {
			closeAll(ps, conn);
		}

	}

	@Override
	public void deleteBook(String isbn) throws SQLException, BookNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		conn = getConnect();

		String query = "DELETE FROM book WHERE isbn = ?";
		ps = conn.prepareStatement(query);

		ps.setString(1, isbn);

		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new BookNotFoundException();
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public Book findByBook(String isbn, String title) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Book temp;

		conn = getConnect();

		String query = "SELECT isbn, title, author , publisher, price FROM book WHERE isbn = ? AND title = ?";

		ps = conn.prepareStatement(query);

		ps.setString(1, isbn);
		ps.setString(2, title);

		rs = ps.executeQuery();
		rs.next();
		temp = new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"), rs.getString("publisher"),
				rs.getInt("price"));

		closeAll(rs, ps, conn);

		return temp;
	}

	@Override
	public ArrayList<Book> findByWriter(String writer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Book> temp = new ArrayList<Book>();

		conn = getConnect();

		String query = "SELECT isbn, title, author , publisher, price FROM book where author=?";
		ps = conn.prepareStatement(query);

		ps.setString(1, writer);

		rs = ps.executeQuery();

		while (rs.next()) {
			temp.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}
		closeAll(rs, ps, conn);

		return temp;
	}

	@Override
	public ArrayList<Book> findByPublisher(String publisher) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Book> temp = new ArrayList<Book>();

		conn = getConnect();

		String query = "SELECT isbn, title, author , publisher, price FROM book where publisher=?";
		ps = conn.prepareStatement(query);

		ps.setString(1, publisher);

		rs = ps.executeQuery();

		while (rs.next()) {
			temp.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}
		closeAll(rs, ps, conn);

		return temp;
	}

	@Override
	public ArrayList<Book> findByPrice(int min, int max) throws SQLException, InvalidInputException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Book> temp = new ArrayList<Book>();

		if (min > max || min < 0 || max < 0) {
			throw new InvalidInputException();
		}

		conn = getConnect();

		String query = "SELECT isbn, title, author , publisher, price FROM book WHERE price BETWEEN ? AND ?";
		ps = conn.prepareStatement(query);

		ps.setInt(1, min);
		ps.setInt(2, max);

		rs = ps.executeQuery();

		while (rs.next()) {
			temp.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}
		closeAll(rs, ps, conn);

		return temp;
	}

	@Override
	public void discountBook(int per, String publisher) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			String query = "UPDATE book SET (price * (1-?/100)) WHERE publisher=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, per);
			ps.setString(2, publisher);
			ps.executeUpdate();
		} finally {
			closeAll(ps, conn);
		}

	}

	@Override
	public void printAllInfo() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Book> temp = new ArrayList<Book>();

		conn = getConnect();

		String query = "SELECT isbn, title, author , publisher, price FROM book";
		ps = conn.prepareStatement(query);

		rs = ps.executeQuery();

		while (rs.next()) {
			temp.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getString("publisher"), rs.getInt("price")));
		}

		closeAll(rs, ps, conn);

		System.out.println(temp);

	}

}
