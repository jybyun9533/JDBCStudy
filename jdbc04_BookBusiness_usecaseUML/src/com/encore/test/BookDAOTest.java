package com.encore.test;

import java.sql.SQLException;

import com.encore.dao.impl.BookDAOImpl;
import com.encore.exception.BookNotFoundException;
import com.encore.exception.DuplicateISBNException;
import com.encore.exception.InvalidInputException;
import com.encore.vo.Book;

public class BookDAOTest {

	public static void main(String[] args)
			throws SQLException, DuplicateISBNException, BookNotFoundException, InvalidInputException {

		BookDAOImpl service = BookDAOImpl.getInstance();

		Book book1 = new Book("123", "aaa", "bbb", "ccc", 10000);
		Book book2 = new Book("456", "aaa2", "bbb2", "ccc2", 20000);

		System.out.println(service.findByPrice(10060, 10000));

		service.printAllInfo();
	}

}
