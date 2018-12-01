package Except;

public class NegativeNumberException extends Exception {

	public NegativeNumberException() {
		super("Negative number exception!");
		
	}
	
	public NegativeNumberException(String message) {
		super(message);
		
	}
	
	
}
