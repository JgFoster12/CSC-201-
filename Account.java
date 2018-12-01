package Except;

public class Account {
	private double balance;

	
	public Account() {
		
	}

	public Account(double initialDeposit) {
		
		balance= initialDeposit;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double deposit(double amount) throws BadAmountException{
		if(amount < 0)
			throw new BadAmountException("Amount must be greater than or equal to 0");
		else
			balance += amount;
		
		return balance;
		
	}
	
	public double withdraw(double amount) throws BadAmountException{
		if(amount > balance || amount < 0)
			throw new BadAmountException("Either your amount you would like to withdraw exceeds your balance or you entered a negative number.");
		else
			balance -= amount;
		
		return balance;
	}
	
	
}
