package Except;

import java.util.InputMismatchException;
import java.util.Scanner;
public class InputMisMatchDemo {

	public static void main(String[] args) {
		int num = 0;
		Scanner input = new Scanner(System.in);
		
		try {
			System.out.println("Enter a number");
			num = input.nextInt();
			System.out.println(num);
		}
		catch(InputMismatchException e) {
			System.out.println("You entered a string! You were supposed to enter an int!");
			System.out.println("Try again");
			tryAgain();
			
		}
		finally {
			System.out.println("You messed up twice... Ending program.");
		}

	}
	
	public static void tryAgain(){
		int num = 0;
		Scanner input = new Scanner(System.in);
		
		try {
			System.out.println("Enter a second number");
			num = input.nextInt();
			System.out.println(num);
		}
		catch(InputMismatchException e) {
			
		}
		
	}

}
