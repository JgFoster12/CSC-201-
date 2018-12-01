package Except;

public class BadAmountException extends Exception {

	public BadAmountException() {
		super("You entered a bad Amount");
		
	}

	public BadAmountException(String message) {
		super(message);
		
	}
	
	
}
