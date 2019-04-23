package Accounts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class Account {
	
	protected double Amount;
	protected int AccountNumber;
	protected String Username;
	protected String PW;
	protected String Approval;
	
	
	public Account()
	{
		System.out.println("here1");
	}
	
	public Account(String User, String Pass, boolean log) {
		
		
		String choice = "";
		
		if (log == true && logon(User,Pass))
		{
			
			
			try(Scanner S1 = new Scanner(System.in))
			{
				while(choice.equals("quit") != true && menu.Display(this.Approval))
				{
					choice = S1.nextLine();
					Options(choice);
				}
				
				
			}
		}
		
		
	}
	
	
	public Account(String User, String Pass)
	{
		System.out.println("new Account creation");
		
		if(validate(User) == true)
		{
			this.Username = User;
			this.Amount = 0;
			this.PW = Pass;
			this.Approval = "pending";
			GenerateAccountNumber();
			WriteLocal();
			System.out.println("created");
			
			logon(User, Pass);
		}
		else {System.out.println("username given is already in use");}

	}
	
	public void Options(String choice)
	{
			if(choice.toLowerCase().trim().equals("d"))
			{
				Deposit();
			}
			else if(choice.toLowerCase().trim().equals("w"))
			{
				Withdraw(0.0);
			}
			else if(choice.toLowerCase().trim().equals("t"))
			{
				TransferFunds();
			}
	}
		
		
	
	
	
	public boolean logon(String Username, String Password)
	{
		
		try(BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{
			String Entry = FindAcc.readLine();
			while(FindAcc.equals(null) != true) 
			{
				String Fields[] = Entry.split(",");
				
				if(Username.trim().equals(Fields[0]) && Password.trim().equals(Fields[1]))
				{
					System.out.println("logged on as " + Username);
					this.Username = Username;
					this.AccountNumber = Integer.parseInt(Fields[4]);
					this.Approval = Fields[3];
					this.Amount = Double.parseDouble(Fields[2]);
					return true;
					
				}
				Entry = FindAcc.readLine();
			}
			
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
		
		
	}
	
	public void Deposit() 
	{
		System.out.println("how much would you like to deposit? :");
		try (BufferedReader Reader = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{
			@SuppressWarnings("resource")
			Scanner S1 = new Scanner(System.in);
			double Amount = S1.nextDouble();
			if(Amount>=0) 
			{
				
				
				this.Amount+=Amount;
				WriteLocal();
			}
			else
			{
				System.out.println("must add a number >=0");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Deposit(double Amount, String Username, int AccountNumber)
	{
		try (BufferedReader Reader = new BufferedReader(new FileReader("src/logs/accounts.txt/"))){
			
			String line = Reader.readLine();
			
			while(line != null)
			{
				if(line.contains(Username) && line.contains(Integer.toString(AccountNumber)))
				{
					// add funds to the account specified
					System.out.println(Amount + " has been given to " + Username);
					return;
				}
			}
			Deposit();
			System.out.println("account specified was not found");
		} 
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e){ e.printStackTrace();}
	}
	
	public boolean Withdraw(double amount) {
		
		System.out.println("how much would you like to withdraw? :");
		double cash = 0.0;
		
		
		if(cash>0 && this.Amount>=cash)
		{
			this.Amount -= cash;
			WriteLocal();
			return true;
		}
		else if(cash>=0 && this.Amount<cash)
		{
			System.out.println("Not enough left in account to withdraw");
			return false;
		}
		else 
		{
			System.out.println("Amount to withdraw must be >=0");
			return false;
		}
	}
	
	public void TransferFunds()
	{
		
		System.out.println("please enter the amount to transfer,"
				+ "\n" + "the username of the recipient and their Account number"
				+ "\n" + "seperated by commas (ex. 123.10,mike123,12456789 )");
		
		Scanner S1 = new Scanner(System.in);
		String AccountInfo = S1.next();
		String[] fields = AccountInfo.split(",");
		
		if (Withdraw(Double.parseDouble(fields[0])) == true) // if we can withdraw the amount
		{	
			//then we are able to put it in a different account
			Deposit(Double.parseDouble(fields[0]),fields[1],Integer.parseInt(fields[2]));
		}
		else
		{
			System.out.println("insufficient funds to withdraw");
		}
		
		S1.close();
		return;
	}
	
	public void WriteLocal() {
		
		String meep = (this.Username 
						+ "," + this.PW
						+ "," + this.Amount
						+ "," + this.Approval
						+ "," + this.AccountNumber
						);
		
		System.out.println(meep);
		
		try(FileWriter f = new FileWriter("src/logs/accounts.txt/", true); 
				BufferedWriter b = new BufferedWriter(f); 
				PrintWriter p = new PrintWriter(b);)
		{
			p.println(meep);
		}
		catch(IOException e) {e.printStackTrace();}
		
	}
	
	public boolean validate(String user)
	{	
		try (BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{
			String line = FindAcc.readLine();
				
			while(line.equals(null) != false)
			{
				String [] attribs = line.split(",");
				if(attribs[0].equals(user))
				{
					System.out.println("false");
					return false;
				}
				line = FindAcc.readLine();
			}
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		
		return true;
	}
	
	public void GenerateAccountNumber()
	{
	try (BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{	
			String line = FindAcc.readLine();
			int Number = 0;
		
			while(line!=null)
			{
				String fields[] = line.split(",");
				
				if (Number < Integer.parseInt(fields[(fields.length - 1)]))
					{
						Number = Integer.parseInt(fields[fields.length - 1]);
					}
				
				this.AccountNumber = Number + 1;
				line = FindAcc.readLine();
			}
		}
	catch(FileNotFoundException e) {e.printStackTrace();}
	catch (IOException e) {e.printStackTrace();}
	
	}
}

