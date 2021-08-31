package broker.twotier.exception;

public class InvalidTransactionException extends Exception {

	public InvalidTransactionException() {
		this("InvalidTransactionException :: 옳바르지 않은 거래입니다.");
	}

	public InvalidTransactionException(String message) {
		super(message);
	}
	
}
