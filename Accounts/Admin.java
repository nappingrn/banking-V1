package Accounts;
import java.io.*;

public class Admin extends employee{

	public boolean AdminToken = true;
	
	public Admin()
	{
		
		this.Approval = "Admin";
		
	}
	
	public void Log(String log)
	{
		try(FileWriter f = new FileWriter("src/logs/accounts.txt/", true); 
				BufferedWriter b = new BufferedWriter(f); 
				PrintWriter p = new PrintWriter(b);)
		{
			p.println(log);
		} 
		catch (IOException e) {e.printStackTrace();}
	}
	public void Deposit(int AccNumber, double Amount) 
	{
	if(Amount < 0){System.out.println("stop trying to enter negatives, jerk"); return;}
			
			String replace = "";
			String[] ReplaceItems;
			try (BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
			{	
				String line = FindAcc.readLine();
			
				while(line!=null)
				{
					String fields[] = line.split(",");
					
					if(Integer.parseInt(fields[fields.length-1]) == AccNumber)
					{
						ReplaceItems = line.split(",");
						ReplaceItems[ReplaceItems.length-1] = (""+Amount);
				
						if(Amount + Double.parseDouble(ReplaceItems[ReplaceItems.length-1]) >= 0)
						{
							ReplaceItems[ReplaceItems.length-1] = 
									(" " + (Amount - Double.parseDouble(ReplaceItems[ReplaceItems.length-1])));
						}
						
						for(int i = 0; i < ReplaceItems.length;i++)
						{
							replace += ReplaceItems[1];
						}
					}
	
					line = FindAcc.readLine();
				}
				if (replace != null)
				{
					
					System.out.println(replace);
					Log(replace);
				}
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void Withdraw(int AccNumber, double Amount)
	{
		if(Amount < 0){System.out.println("stop trying to enter negatives, jerk"); return;}
		
		String replace = "";
		String[] ReplaceItems;
		try (BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{	
			String line = FindAcc.readLine();
		
			while(line!=null)
			{
				String fields[] = line.split(",");
				
				if(Integer.parseInt(fields[fields.length-1]) == AccNumber)
				{
					ReplaceItems = line.split(",");
					ReplaceItems[ReplaceItems.length-1] = (""+Amount);
			
					if(Amount - Double.parseDouble(ReplaceItems[ReplaceItems.length-1]) >= 0)
					{
						ReplaceItems[ReplaceItems.length-1] = 
								(" " + (Amount - Double.parseDouble(ReplaceItems[ReplaceItems.length-1])));
					}
					
					for(int i = 0; i < ReplaceItems.length;i++)
					{
						replace += ReplaceItems[1];
					}
				}

				line = FindAcc.readLine();
			}
			if (replace != null)
			{
				
				System.out.println(replace);
				Log(replace);
			}
		}
	catch(FileNotFoundException e) {e.printStackTrace();}
	catch (IOException e) {e.printStackTrace();}
	}
	
	public boolean Validate(int AccNumber) {return true;}
	
	public void DeleteAccount(int AccNumber) 
	{
		/*
		 * When we are checking lines, write the line right after reading it
		 * unless it contains the account number that we are looking for
		 * if it does, just read the next line, effectively deleting the 
		 * item by not printing it when we were supposed to.
		 * */
		
		try (BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{	
			String ReplaceItems[];
			String line = FindAcc.readLine();
		
			while(line!=null)
			{
				ReplaceItems = line.split(",");
				
				if(Integer.parseInt(ReplaceItems[ReplaceItems.length-1]) == AccNumber)
				{
					
				}
				else
				{
					System.out.println(line);
				}
				line = FindAcc.readLine();
			}

			} catch (FileNotFoundException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();}
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
