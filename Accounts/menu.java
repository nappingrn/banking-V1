package Accounts;

public class menu {

		public static boolean Display(String S)
		{
			boolean Valid = false;
			if(S.equals("Account") ||S.equals("Employee")|| S.equals("Admin") )
			{
				System.out.println("\nUser operations : (W to withdraw) :: (D to deposit) :: (T to transfer)");
						
				Valid = true;
				
			}
			if(S.equals("Employee")|| S.equals("Admin"))
			{
				System.out.println("Employee options"
						+ ": L to list all of the employees:: "
						+ "V to start to verify process");
				
			}
			if(S.equals("Admin"))
			{
				System.out.println("Admin options: S to show all users:: R to remove accounts"
						+ ":: M to make employees");
				
			}
			if(S.equals("pending"))
			{
				System.out.println("you are not approved yet.");
			}
			
			System.out.print("quit to quit the program and log out: ");
			return Valid;
		}
	
}
