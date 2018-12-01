package Except;

import java.util.Scanner;

public class TwoCatchesDemo {

	public static void main(String[] args) {
		try {
			
			System.out.println("Enter number of widgets produced");
			Scanner in = new Scanner(System.in);
			int widgets = in.nextInt();
			
			if(widgets < 0)
				throw new NegativeNumberException("widgets");
			
			System.out.println("How many were deffective?");
			int defect = in.nextInt();
			if(defect < 0)
				throw new NegativeNumberException("Defective " + "widgets");
			
			double ratio = exceptionalDivision(widgets, defect);
			System.out.println("One in every " + ratio + "widgets is defective");
			
		}
		catch(DivideByZeroException e) {
			System.out.println("Congrats, none were defective!");
		}
		
		catch(NegativeNumberException e) {
			System.out.println("Cannot have a negative number of: " + e.getMessage());
			
		}
		
	}
	
	public static double exceptionalDivision(double numerator, double denominator) throws DivideByZeroException{
		
		if(denominator == 0)
			throw new DivideByZeroException();
		
		return numerator / denominator;
	}
}
