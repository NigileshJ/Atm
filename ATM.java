package atm;

import java.util.Scanner;

class BankAccount {

	String name;
	String userName;
	String password;
	String accountNo;
	float balance = 100000;
	int transactions = 0;
	String transactionHistory = "";


	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Your Name: ");
		this.name = sc.nextLine();
		System.out.print("\nEnter the Username you want: ");
		this.userName = sc.nextLine();
		System.out.print("\nEnter Strong Password: ");
		this.password = sc.nextLine();
		System.out.print("\nEnter Your Account Number - ");
		this.accountNo = sc.nextLine();
		System.out.println("\nRegistration completed");
                System.out.println("\nLogin to find whether you account has been created or not");
                
	}

	public boolean login() {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		while ( !isLogin ) {
			System.out.print("\nEnter Your Username - ");
			String Username = sc.nextLine();
			if ( Username.equals(userName) ) {
				while ( !isLogin ) {
					System.out.print("\nEnter Strong Password - ");
					String Password = sc.nextLine();
					if ( Password.equals(password) ) {
						System.out.print("\n Your account has been Login successful!!");
						isLogin = true;
					}
					else {
						System.out.println("\nIt's Incorrect Password");
					}
				}
			}
			else {
				System.out.println("\n Your Username has not been found");
			}
		}
		return isLogin;
	}

	public void withdraw() {

		System.out.print("\nEnter the amount you want to withdraw:");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		try {

			if ( balance >= amount ) {
				transactions++;
				balance -= amount;
				System.out.println("\n Amount has been Withdraw Successfully");
				String str = amount + " Rs Withdrawed\n";
				transactionHistory = transactionHistory.concat(str);

			}
			else {
				System.out.println("\nYou have Insufficient Balance to withdraw");
			}

		}
		catch ( Exception e) {
		}
	}

	public void deposit() {

		System.out.print("\nEnter the amount you want to deposit: ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();

		try {
			if ( amount <= 100000f ) {
				transactions++;
				balance += amount;
				System.out.println("\nAmount has been Successfully Deposited");
				String str = amount + " Rs deposited\n";
				transactionHistory = transactionHistory.concat(str);
			}
			else {
				System.out.println("\nSorry Limit is 100000.00");
			}

		}
		catch ( Exception e) {
		}
	}

	public void transfer() {

		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Receipent's Name:");
		String receipent = sc.nextLine();
		System.out.print("\nEnter the amount you want to transfer:");
		float amount = sc.nextFloat();

		try {
			if ( balance >= amount ) {
				if ( amount <= 50000f ) {
					transactions++;
					balance -= amount;
					System.out.println("\nAmount has been Successfully Transfered to " + receipent);
					String str = amount + " Rs transfered to " + receipent + "\n";
					transactionHistory = transactionHistory.concat(str);
				}
				else {
					System.out.println("\nSorry...Limit is 50000.00");
				}
			}
			else {
				System.out.println("\nYou have Insufficient Balance");
			}
		}
		catch ( Exception e) {
		}
	}

	public void checkBalance() {
		System.out.println("\n" + balance + " Rs");
	}

	public void transHistory() {
		if ( transactions == 0 ) {
			System.out.println("\nEmpty");
		}
		else {
			System.out.println("\n" + transactionHistory);
		}
	}
}


public class ATM {


	public static int takeIntegerInput(int limit) {
		int input = 0;
		boolean flag = false;

		while ( !flag ) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;

				if ( flag && input > limit || input < 1 ) {
					System.out.println("Choose the number between 1 to " + limit);
					flag = false;
				}
			}
			catch ( Exception e ) {
				System.out.println("Enter only integer value");
				flag = false;
			}
		};
		return input;
	}


	public static void main(String[] args) {

		System.out.println("\nWELCOME TO ATM SYSTEM\n");
		System.out.println("1.Register \n2.Exit");
		System.out.print("Enter Your Choice: ");
		int choice = takeIntegerInput(2);

		if ( choice == 1 ) {
			BankAccount b = new BankAccount();
			b.register();
			while(true) {
				System.out.println("\n1.Login \n2.Exit");
				System.out.print("Enter Your Choice:");
				int ch = takeIntegerInput(2);
				if ( ch == 1 ) {
					if (b.login()) {
						System.out.println("\n\nWELCOME" + b.name + "\n");
						boolean isFinished = false;
						while (!isFinished) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.print("\nEnter Your Choice - ");
							int c = takeIntegerInput(6);
							switch(c) {
								case 1:
								b.withdraw();
								break;
								case 2:
								b.deposit();
								break;
								case 3:
								b.transfer();
								break;
								case 4:
								b.checkBalance();
								break;
								case 5:
								b.transHistory();
								break;
								case 6:
								isFinished = true;
								break;
							}
						}
					}
				}
				else {
					System.exit(0);
				}
			}
		}
		else {
			System.exit(0);
		}



	}
}