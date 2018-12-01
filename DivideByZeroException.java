package Except;

public class DivideByZeroException extends Exception {
	
	public DivideByZeroException() {
		super("Divding by zero");
	}
	public DivideByZeroException(String message) {
		super(message);
	}

}
