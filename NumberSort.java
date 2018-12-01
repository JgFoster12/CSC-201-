import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class NumberSort {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner inputStream = null;
		System.out.print("Enter your file name");
		String fileName = input.nextLine();
		String lineNum = " ";
		ArrayList<Integer> numbersFromFile = new ArrayList<Integer>();
			
		try {
			inputStream = new Scanner(new File(fileName));
			while(inputStream.hasNextLine()) {
				lineNum = inputStream.nextLine();
				
				numbersFromFile.add(Integer.parseInt(lineNum));
				Collections.sort(numbersFromFile, null);
							
							
			}
			
			int total = 0;
			
			for(int x: numbersFromFile) {
				total+= x;
			}
			
			int largestNum = numbersFromFile.get(numbersFromFile.size() -1 );
			int smallestNum = numbersFromFile.get(numbersFromFile.size() - numbersFromFile.size());
			int avg = total / numbersFromFile.size();
			inputStream.close();
			input.close();
			
			System.out.printf("The Smallest number is: %d -- The largest Number is: %d -- The average of all the numbers is: %d" ,
					smallestNum, largestNum, avg);		}
		
		catch(FileNotFoundException e) {
			System.out.println("Could not read from file " + fileName);
		}


	}

}
