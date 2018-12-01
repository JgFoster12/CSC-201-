package Except;
import java.util.InputMismatchException;
import java.util.Scanner;



public class AmountDemo {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Account a = new Account(200);
		double deposit = 0;
		double withdraw = 0;
		
		try {
			int sent;
			System.out.println("Enter 1 to view your balance, 2 to withdraw, 3 to deposit, or 4 to exit");
			sent = input.nextInt();
			switch(sent) {
			case 1:
				System.out.println("Your balance is: $" + a.getBalance());
				break;
			
			case 2:
				System.out.println("Enter amount you would like to withdraw");
				withdraw = input.nextDouble();
				a.withdraw(withdraw);
				System.out.println("Your balance is: $" + a.getBalance());
				break;
			
			case 3:
				System.out.println("Enter amount you would like to deposit");
				deposit = input.nextDouble();
				a.deposit(deposit);
				System.out.println("Your balance is: $" + a.getBalance());
				break;
				
			case 4:
				System.exit(0);
			}
			
			
		}
		catch(BadAmountException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("Wrong input");
		}
		 
		

	}

}
