import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class NewFile {

	public static void main(String[] args) {
		ArrayList<Integer> numbersFromFile= new ArrayList<Integer>();
		Scanner input = new Scanner(System.in);
		Scanner inputStream = null;
		PrintWriter outputStream = null;
		String readFile = " ";
		String writeFile = " ";
		String line = " ";
		
		System.out.println("Enter the File you would like to read from");
		readFile = input.nextLine();
		System.out.println("Enter the file you would like to write the numbers from " + readFile + " to");
		writeFile = input.nextLine();
		
		try {
			File file = new File(writeFile);
			inputStream = new Scanner(new File(readFile));
			outputStream = new PrintWriter(file);
			while(inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				numbersFromFile.add(Integer.parseInt(line));
				Collections.sort(numbersFromFile, null);
			}
			
			for(int i = 0; i < numbersFromFile.size(); i++) {
				if(removeRepeat(numbersFromFile, i) == false) {
					numbersFromFile.remove(i);
										
				}
								
			}
			
			outputStream.println(numbersFromFile);
			input.close();
			inputStream.close();
			outputStream.close();
			
			System.out.println(numbersFromFile);
					
		}
		catch(FileNotFoundException e) {
			System.out.println("There is a problem with one of the files");
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean removeRepeat(ArrayList<Integer> numbersFromFile, int val) {
		for(val = 0; val < numbersFromFile.size(); val++) {
			if(numbersFromFile.indexOf(val) == numbersFromFile.indexOf(val-1) ) {
				return false;
			}
		}
		return true;
	}
	

}
