package Except;

import java.util.Scanner;

public class ExceptionDemo {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		try {
			
		
			System.out.println("Enter number of goals scored");
			int goals = in.nextInt();
			
			System.out.println("Enter number of assists");
			int assist = in.nextInt();
			
			int points = goals + assist;
			
			if(goals < 0 || assist < 0)
				throw new Exception("Hey bud.. a little dusty? You entered a negative number.");
			
			System.out.printf("%s%d%n%s%d%n%s%d%n", "Goals: ", goals, "Assists: ", assist, "Points: ", points);			
			}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Re-enter a positive number ");
		}
	}

}
