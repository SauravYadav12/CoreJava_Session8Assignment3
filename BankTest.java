class Bank {

		int balance;

		public Bank(int balance) {
			
			this.balance=balance;
}

     public synchronized void withdraw(int amount ){  //Implementing synchronized while withdrawing the amount
	
	
		if(balance<amount){
			System.out.println("balance is not available");
		}
		else
		{
		System.out.println("available balance is "+balance);
		  int newBalance = balance - amount;
		 
		                      try {  // Allowing the thread to sleep for 10 sec so that deposit can happen
		              			Thread.sleep(1000);
		              		} catch (InterruptedException e) {
		              			
		              			e.printStackTrace();
		              		}
		              		this.balance=newBalance;
							System.out.println("Withdraw Amount is  "+ amount);
		              		System.out.println("your balance is "+newBalance);
			}              
		}
	
	public synchronized void deposit(int amount){  //Implementing synchronized while depositing the amount
		int temp=this.balance;
		
		temp=temp+amount;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		this.balance=temp;
		System.out.println("Amount deposited "+amount);
		System.out.println("your balance is "+balance);
		}
	}

class Transaction extends Thread {

	private Bank bank;

	public Transaction(Bank bank) {  // Creating a constructor for deposit and withdraw using thread
		this.bank = bank;
	}
	@Override
	public void run() {  // implementing the run method
		
		bank.deposit(2000);
		bank.withdraw(400);	
	}
}

class BankTest {
	public static void main(String[] args) {
		
		Bank b=new Bank(2000);
		Transaction t1=new Transaction(b);  
		Transaction t2=new Transaction(b);
		t1.start();
		//t2.start();
		b.withdraw(10000);
		b.deposit(100);
		
	}
}
