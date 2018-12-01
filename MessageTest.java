package Except;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MessageTest {
	
	
	public static void main(String[] args) {
		String message = null;
		String sentinal = null;
		
		while(true) {
			try {
				Scanner input = new Scanner(System.in);
			
			 {
				 System.out.println("Enter your message under 20 characters");
				 message = input.nextLine();
				
				 if(message.length() > 20) {
					throw new MessageTooLongException("You entered " + (message.length() - 20 )+  " too many characters!");
					
			}			
				 System.out.println("your message: " + message);
				
				 System.out.println("Would you like to continue? Enter any String if so, if not, enter no ");
				 sentinal = input.nextLine();
					
				 if(sentinal.equalsIgnoreCase("no") == true) {
					 System.out.println("Closing the program");
					 System.exit(0);
			}
				
			
			}
												
		}
		
			catch(MessageTooLongException e) {
				Scanner input = new Scanner(System.in);
				System.out.println(e.getMessage());
				
				System.out.println("Would you like to continue? Enter any String if so, if not, enter no ");
				sentinal = input.nextLine();
				
				if(sentinal.equalsIgnoreCase("no") == true) {
					 System.out.println("Closing the program");
					 System.exit(0);
			}
						
		}
		
		}
		
		
		}
	

	
		}
	
	
	

