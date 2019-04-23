package Accounts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class employee extends Account { // btw admin should extend off this
	
	
	
	public employee()
	{
		this.Approval = "employee";
	}
	
	public void verify(String TheUser, boolean choice)
	{
		try (BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{	
			String line = FindAcc.readLine();
		
			while(line!=null)
			{
				String fields[] = line.split(",");
				
				if (TheUser == fields[0] && fields[fields.length-1] == "pending")
				{
					this.Username = fields[(fields.length - 2)];
				}
				else 
				{
					System.out.println("User Already validated.");	
				}
				line = FindAcc.readLine();
			}
		}
	catch(FileNotFoundException e) {e.printStackTrace();}
	catch (IOException e) {e.printStackTrace();}
	
		
	}
	
	public void ListUnapproved()
	{
		try (BufferedReader FindAcc = new BufferedReader(new FileReader("src/logs/accounts.txt/")))
		{	
			String line = FindAcc.readLine();
		
			while(line!=null)
			{
				String fields[] = line.split(",");
				
				if (fields[fields.length-1] == "pending")
				{
					for (int i = 0; i<fields.length; i++)
					{
						System.out.println(fields[i]);
						
					}
					System.out.println("");
				}

				line = FindAcc.readLine();
			}
		}
	catch(FileNotFoundException e) {e.printStackTrace();}
	catch (IOException e) {e.printStackTrace();}
	}

}