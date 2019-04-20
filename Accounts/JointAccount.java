package Accounts;
	
public class JointAccount extends Account {

	private String Username2;
	private String PW2;
	
	
	public JointAccount()
	{
		this.Username = "new1";
		this.PW = "new1";
		this.Username2 = "new2";
		this.PW2 = "new2";
		this.AccountNumber = -99;
		this.Approval = "pending";
		
	}
	public JointAccount(String User1, String Pass1, String User2, String Pass2)
	{
		this.Username = User1;
		this.PW = Pass1;
		this.Username2 = User2;
		this.PW2 = Pass2;
		GenerateAccountNumber();
		this.Approval = "pending";
		
	}
	
	public void WriteLocal()
	{
		
		String ToFileString = ("{" + this.Username 
				+ "," + this.PW 
				+ "," + this.Username2 
				+ "," + this.PW2
				+ "," + this.AccountNumber 
				+ "," + this.Amount
				+ "," + this.Approval
									+ "}");
		
		System.out.println(ToFileString);
		
	}
	
}
