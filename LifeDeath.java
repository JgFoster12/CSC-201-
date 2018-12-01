import java.io.*;
import java.math.*;
import java.util.Scanner;

public class LifeDeath {
	public static void main (String[] args) {
		String line = "";
		String newArray[];
		Scanner input = new Scanner(System.in);
		Scanner inputStream = null;
		
		int gender = 0;
		int ageIn = 0;
	
		
		try {
			inputStream = new Scanner(new File("LifeDeathProbability.txt"));
			
			while(inputStream.hasNextLine()) {
				//Separates three entities by a space
				line = inputStream.nextLine();
				newArray = line.split(" ");
				//Assigns each index to respective variable
				@SuppressWarnings("unused")
				int age = Integer.parseInt(newArray[0]);
				double male = Double.parseDouble(newArray[1]);
				double female = Double.parseDouble(newArray[2]);
				//variable to keep track of random number
				
				System.out.println("Enter 1 if you are a male or 2 if you are a female");
				gender = input.nextInt();
				System.out.println("Enter your age");
				ageIn = input.nextInt();
				
				switch(gender) {
				case 1:
					guessAge(ageIn,male);
					break;
			
				case 2:
					guessAge(ageIn, female);
					break;
				}			
				
			}
		}
		catch(IOException e) {
			System.out.println("There was a problem with the file");
			e.printStackTrace();
		}
		
		input.close();
		inputStream.close();
		
	}
	
	public static void guessAge(int ageIn, double gender) {
		
		
		while(true) {
			//Reduced the margin because Math.random chooses numbers closer to one, resulting in the same answer every time.
			double sent = Math.random() * .1;
			if(sent > gender && ageIn < 120) {
				ageIn++;
				
			}
			
			else{				
				System.out.print("You are living to be: " + ageIn);
				System.exit(0);
			}
		}
		
	}

}
/*
 * Sample Output
 * Enter 1 if you are a male or 2 if you are a female
2
Enter your age
45
You are living to be: 54 */
