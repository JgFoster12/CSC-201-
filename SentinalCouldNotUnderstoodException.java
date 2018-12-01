package Except;

public class SentinalCouldNotUnderstoodException extends Exception {

	public SentinalCouldNotUnderstoodException() {
		super("You entered a non String variable! System Exiting");
		
	}

	public SentinalCouldNotUnderstoodException(String message) {
		super(message);
		
	}
	
}
