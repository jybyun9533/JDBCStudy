package com.encore.exception;

public class DuplicateISBNException extends Exception {

	public DuplicateISBNException() {
		this("DuplicateISBNException :: 이미 존재하는 ISBN 입니다.");
	}

	public DuplicateISBNException(String message) {
		super(message);
	}
	
}
