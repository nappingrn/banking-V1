package Accounts;

public class employee extends Account { // btw admin should extend off this
	
	
	
	public employee()
	{
		this.Approval = "employee";
	}
	
	public void verify(String TheUser, boolean choice)
	{
		// search file for username, change approval to "accepted"
		//or "denied" based on the boolean received
		
	}
	
	public void ListUnapproved()
	{
		//show list of users that are not approved yet
		// only show username and approval
		
	}

}