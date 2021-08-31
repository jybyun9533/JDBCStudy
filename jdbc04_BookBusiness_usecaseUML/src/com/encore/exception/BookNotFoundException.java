package com.encore.exception;

public class BookNotFoundException extends Exception {
	
	public BookNotFoundException() {
		this("BookNotFoundException :: 책을 찾을 수 없습니다.");
	}

	public BookNotFoundException(String message) {
		super(message);
	}

}
