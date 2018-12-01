import java.util.Scanner;
import java.io.*;


public class StupendousProgrammingSkills {
	public static void main(String[] args) {
		String dousFile = "";
		Scanner input = new Scanner(System.in);
		Scanner inputStream = null;
		PrintWriter outputStream = null;
		System.out.println("Name a file where you would like to write all the words ending in 'dous'");
		dousFile = input.nextLine();	
		String line = "";
		String test = "dous";
	
		
		try {
			inputStream = new Scanner(new File("words.txt"));
			outputStream = new PrintWriter(new FileWriter("output.txt"));
			
			while(inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				
				
				if(line.length() > 4 && line.substring(line.length() - 4).equals(test)) {
					System.out.println(line);
					outputStream.println(line);
				}
					
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		inputStream.close();
		input.close();
		outputStream.close();
	}
	}

/*Sample Output
 * Name a file where you would like to write all the words ending in 'dous'
cat.txt

hazardous
horrendous
jeopardous
stupendous
tremendous
*/
