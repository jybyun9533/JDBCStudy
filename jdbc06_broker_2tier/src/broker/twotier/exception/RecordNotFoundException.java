package broker.twotier.exception;

public class RecordNotFoundException extends Exception {
	public RecordNotFoundException() {
		this("RecordNotFoundException :: 내역을 찾을 수 없습니다..");
	}

	public RecordNotFoundException(String message) {
		super(message);
	}
}
