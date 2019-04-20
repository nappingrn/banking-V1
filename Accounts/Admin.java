package Accounts;
import java.io.*;

public class Admin extends employee{

	public boolean AdminToken = true;
	
	public Admin()
	{
		
		this.Approval = "Admin";
		
	}
	
	public void revupthosefryers()
	{
		System.out.println("welcome admin what are we going to do?");
		System.out.println("Log(L), Deposit(D), Withdraw(W), Delete Account(A)");
		System.out.println("Transfer Funds(T), Make Employee(E), Show Users(S)");
		
	}
	public void Log(int AccNumber) {}
	public void Deposit(int AccNumber, double Amount) {}
	public void Withdraw(int AccNumber, double Amount) {}
	public boolean Validate(int AccNumber) {return true;}
	public void DeleteAccount(int AccNumber) 
	{
		
		
		
	}
	
	public void TransferFunds(int UserID1, int UserID2, double amount)
	{
		if (UserID1 == UserID2)
		{
			System.out.println("you cannot move funds to and from the same account");
		}
		else if ((Validate(UserID1) && Validate(UserID2)) == true)
		{
			Deposit(UserID1, amount);
			
			Withdraw(UserID2, amount);
			
		}
		
	}
	
	/*
	 * while scanner with the file has the next line we are going to read
	 * the next line and if that line has the account number
	 * that we are looking for then we are going to delete that line
	 * storing said line before hand, add the contents of the line
	 * to an array. check the size to make sure that we are not modifying the 
	 * wrong file, since joint files will have much more fields than
	 * we are trying to parse
	*/
	public void MakeEmployee(int AccNumber)
	{
		int x = 1;
		System.out.println("attempting to employ Account number " + AccNumber);
		
		if (Validate(AccNumber) == true)
		{
			
			//modify user status so its employee
			if(x==1 /* if user != admin && user !=employee*/)
			{
				//approve user if not appproved
				//make user employee 
			}
			else {
				System.out.println("User is already Admin or employee");
			}
		}
		else
		{
			System.out.println("Account number is invalid");
		}
		
	}
	
	public void ShowAll()
	{	
		try 
		{
			BufferedReader ShowFile = new BufferedReader(new FileReader("./logs/accounts/"));
			String line = ShowFile.readLine();
			
			while(line!=null)
			{
				System.out.println(line);
				line = ShowFile.readLine();
			}
			
			ShowFile.close();
		}
		catch(IOException e) {System.out.println("io exception");}
	}
	
	
}
