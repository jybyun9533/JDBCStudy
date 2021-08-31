package com.encore.exception;

public class InvalidInputException extends Exception {

	public InvalidInputException() {
		this("InvalidInputException :: 입력값이 잘못 되었습니다.");
	}

	public InvalidInputException(String message) {
		super(message);
	}
	
}
