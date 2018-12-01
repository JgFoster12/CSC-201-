package Except;

public class MessageTooLongException extends Exception {

	public MessageTooLongException() {
		super("Message too long");
		
	}


	public MessageTooLongException(String message) {
		super(message);
		
	}


	
}
