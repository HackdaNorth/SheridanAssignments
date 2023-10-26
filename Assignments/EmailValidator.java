package sheridan;


import java.util.StringTokenizer;

public class EmailValidator {
	

	public static void main(String[] args) 
	{
		// need empty constructor
	}

	//get email function
	public static String getEmail(String email) throws NumberFormatException, StringIndexOutOfBoundsException
	{
		return email;
	}
	
	//do this after we checked each category seperately
	public static boolean isEmailValid(String email) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
			if (email.matches(pattern) ) 
			   return true;
			
			return false;
		  
	}
	public static boolean isAccountNameValid(String email)
	{
		//new tokenzier for string
		StringTokenizer stringToken = new StringTokenizer(email, "@");
		String AccountName = stringToken.nextToken();
	
		//defining variables
		char[] AccountNameCharArray = new char[AccountName.length()];
	
		int numLowerCase = 0;
		
		//go through each letter
		for(int i=0;i < AccountName.length(); i++) {
		
			//get each char and put into array
			AccountNameCharArray[i] += AccountName.charAt(i);
	
			//if any are lower case then  ++
			if(Character.isLowerCase(AccountNameCharArray[i])) {
				numLowerCase++;
			}
			//does first spot in char array equal a number? false if so
			if(Character.isDigit(AccountNameCharArray[0])) {
				return false;
			}
		}
		
		if (numLowerCase >= 3) 
				return true;
		
		return false;
	}
	
	public static boolean isDomainValid(String email) throws NumberFormatException, StringIndexOutOfBoundsException
	{
		//new tokenzier for string
		StringTokenizer stringToken = new StringTokenizer(email, "@");
		stringToken.nextToken(); //gets string before @
		String DomainAndExtension = stringToken.nextToken(); // gets whole token after @
		
		StringTokenizer stringTokenTwo = new StringTokenizer(DomainAndExtension, ".");
		String Domain = stringTokenTwo.nextToken();

		char[] doaminCharArray = new char[Domain.length()];
		int numLowerCase = 0;
		
		//go through each letter
		for(int i=0;i < Domain.length(); i++) {
			//defining variables
			
			//get each char and put into array
			doaminCharArray[i] +=  Domain.charAt(i);
			//if any are lower case then  ++
			if(Character.isLowerCase(doaminCharArray[i])) {
				numLowerCase++;
			}
			//doesany equal a number?
			if(Character.isDigit(doaminCharArray[i] )) {
				return true;
			}
		}
		if(numLowerCase >= 3 ) // do we have three lower case? if so true
			return true;
		
		return false;
	}
	
	public static boolean isExtensionValid(String email) throws NumberFormatException, StringIndexOutOfBoundsException
	{
		//new tokenzier for string
		StringTokenizer stringToken = new StringTokenizer(email, "@");
		stringToken.nextToken(); //gets string before @
		String DomainAndExtension = stringToken.nextToken(); // gets whole token after @
		
		StringTokenizer stringTokenTwo = new StringTokenizer(DomainAndExtension, ".");
		stringTokenTwo.nextToken();
		String Extension = stringTokenTwo.nextToken();
		

		char[] extensionCharArray = new char[Extension.length()];
		int numLowerCase = 0;
		
		//go through each letter
		for(int i=0;i < Extension.length(); i++) {
			//defining variables
			
			//get each char and put into array
			extensionCharArray[i] =  Extension.charAt(i);
			//if any are lower case then  ++
			if(Character.isLowerCase(extensionCharArray[i])) {
				numLowerCase++;
			}
			//doesany equal a number?
			if(Character.isDigit(extensionCharArray[i]) ) {
				return false;
			}
		}
		if(numLowerCase >= 3 ) // do we have three lower case? if so true
			return true;
		
		return false;
	}
}

		

