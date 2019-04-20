import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Accounts.*;

public class yep{

	public static void main(String args[])
	{
			String UserChoice =  "";
			
			System.out.println("Access Account(Y)");
			System.out.println("Make New Account(N): ");
			
			try(BufferedReader s1 = new BufferedReader( new InputStreamReader(System.in))) {
				UserChoice = s1.readLine();
			
			logon_creds.choice(UserChoice);
			} catch (IOException e) {e.printStackTrace();}
	}
}
	

