package broker.twotier.exception;

public class DuplicateSSNException extends Exception {

	public DuplicateSSNException() {
		this("DuplicateSSNException :: 이미 존재하는 회원입니다.");
	}

	public DuplicateSSNException(String message) {
		super(message);
	}

}
