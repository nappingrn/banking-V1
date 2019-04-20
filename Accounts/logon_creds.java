package Accounts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class logon_creds {


		public static void choice(String in)
		{
			String choice,Username, Password = "";
			try(BufferedReader userInput = new BufferedReader( new InputStreamReader(System.in)))
			{
				if (in.equals("y") || in.equals("Y"))
				{
					
					System.out.println("Welcome Returning user!");
					System.out.print("enter username: ");
					Username = userInput.readLine();
					System.out.print("enter Password: ");
					Password = userInput.readLine();
					
					Account User = new Account(Username, Password, true);
					
				}
				else if(in.equals("n") || in.equals("N"))
				{
					
					System.out.println("would you like to make a single(S) or joint account?(J)");
					choice = userInput.readLine();
					MakeAccount(choice);
					
				}
				else
				{
					System.out.println("you have made an incorrect choice.");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		public static void MakeAccount(String Choice)
		{
			Scanner getDetails = new Scanner(System.in);
			if(Choice.equals("S") || Choice.equals("s"))
			{
				System.out.println("what would you like your account name to be?");
				String TestUser = getDetails.nextLine();
				System.out.println("enter password");
				String TestPass = getDetails.nextLine();
				
				Account NewUser = new Account(TestUser,TestPass);
				
				return;
				
			}
			else if(Choice.equals("J") || Choice.equals("j"))
			{
				System.out.println("what would you like your first account name to be? : ");
				String TestUser = getDetails.nextLine();
				System.out.println("enter first password : ");
				String TestPass = getDetails.nextLine();

				System.out.println("what would you like your second account name to be? : ");
				String TestUser2 = getDetails.nextLine();
				System.out.println("enter second password : ");
				String TestPass2 = getDetails.nextLine();

				JointAccount joint = new JointAccount(TestUser,TestPass,TestUser2,TestPass2);
				
				joint.WriteLocal();
				
			}
			else
			{
				getDetails.close();
				System.out.println("incorrect choice.");
				return;
			}
			
		}
		
		public void logon(String Username, String Password)
		{
			try (BufferedReader Logging = new BufferedReader(new FileReader("file.txt")))
			{
				String Attempt = Logging.readLine();
				if(Attempt.contains(Username) && Attempt.contains(Password))
				{
					String[] identify = Attempt.split(",");
					if(identify.length <=6)
					{
						//do the single account stuff
					}
					else
					{
						// do the joint stuff
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
}

