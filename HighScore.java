import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class HighScore {

	public static void main(String[] args) {
		int highScore = 0;
		String line = "";
		String fileName = "highscores.txt";
		ArrayList<Integer>arrayOfScores = new ArrayList<Integer>();
		Scanner inputStream = null; 
		
		try {
			inputStream = new Scanner(new File(fileName));
			
			while(inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				
				arrayOfScores.add(Integer.parseInt(line));
				Collections.sort(arrayOfScores, null);
				
				highScore = arrayOfScores.get(arrayOfScores.size() - 1);
							
			}
		}
		catch(IOException e) {
			System.out.println("There was a problem with the file");
		}
		inputStream.close();
		System.out.println("The highest Score of them all is: \n" + highScore);

	}

}

/*
 * Sample Output
The highest Score of them all is: 
453333334
 */