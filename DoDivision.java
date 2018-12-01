package Except;

import java.util.Scanner;


public class DoDivision {
	
	private int numerator;
	private int denominator;
	private double quotient;
	
	public static void main(String[] args) {
		
		DoDivision doIt = new DoDivision();
		
		try {
			doIt.doNormalCase();
			
		}
		catch(DivideByZeroException e) {
			System.out.println(e.getMessage());
			doIt.giveSecondChance();
		}
		
	}
	
	public void giveSecondChance() {
		
		System.out.println("Try again");
		System.out.println("Enter numerator");
		Scanner in = new Scanner(System.in);
		numerator = in.nextInt();		
		
		System.out.println("Enter denominator");
		denominator = in.nextInt();
				
		quotient = numerator  / (double)denominator;
		
		System.out.println(numerator + "/" + denominator + " = " + quotient);
		
		if(denominator == 0) {
			System.out.println("You had your chance... I am shutting off now");
			System.exit(0);
		}
		
		quotient = numerator  / (double)denominator;
		
		System.out.println(numerator + "/" + denominator + " = " + quotient);
	}
	
	public void doNormalCase() throws DivideByZeroException{
		
		System.out.println("Enter numerator");
		Scanner in = new Scanner(System.in);
		numerator = in.nextInt();		
		
		System.out.println("Enter denominator");
		denominator = in.nextInt();
		
		if(denominator == 0)
			throw new DivideByZeroException();
		
		quotient = numerator  / (double)denominator;
		
		System.out.println(numerator + "/" + denominator + " = " + quotient);
	}

}
