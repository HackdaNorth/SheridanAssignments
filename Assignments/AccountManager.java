//@author Connor Murray  github.com/HackdaNorth

package com.webstore.silverstyleApp.UseCaseManagers;


//project imports
import com.webstore.silverstyleApp.SilverstyleAppApplication;
//UseCaseMangers imports

//Domain Transfer Objects Imports
import com.webstore.silverstyleApp.DTO.AccountRequest;
import com.webstore.silverstyleApp.DTO.AccountRequestResponse;
//Domain Objects imports
import com.webstore.silverstyleApp.DomainObjects.Account;


public class AccountManager  {
	//Defining private variables
	private int numID = 0;
	private static String userMessage = "";
	//createAcccount
	public AccountRequestResponse createAccount(AccountRequest registerRequest) {
		// test for rain - day paths
		  if(registerRequest != null) { // registerRequest not null
					//validate email, return false, continue if true.
			  	String accEmail = registerRequest.getAccEmail();
			  	String accProvince = registerRequest.getAccProvince();
			  	String accPhone = registerRequest.getAccPhoneNumber();
			  	
				if(AccountManager.emailValidation(accEmail) == false) {
		  		return new AccountRequestResponse(false, userMessage,
					 "createAccount.html"); // new Response
					 //validate Province, return false, continue if true.
				} else if (AccountManager.validateProvince(accProvince) == false) {
					return new AccountRequestResponse(false, userMessage,
					 "createAccount.html");
					 //validate phone #, return false, continue if true.
				} else if (AccountManager.validatePhone(accPhone) == false){
					return new AccountRequestResponse(false, userMessage,
					 "createAccount.html");
				} else { // else if we returned true for past , make account
					Account newAccount = this.buildNewAccount(registerRequest);
					SilverstyleAppApplication.instanceOfAccountPersistanceManager().saveAccountToRepository(newAccount);
					userMessage = "Your Account has been successfully created. \n Your new account email is: " + newAccount.getAccEmail();
					return new AccountRequestResponse(true, userMessage, "accProfile.html");
				}
		  } else { // if accountRequest is null
				userMessage = "You must fill out all fields!";
				return new AccountRequestResponse(false, userMessage,
				 "createAccount.html");
			}
	} // end of createAccount

	public AccountRequestResponse loginToAccount(String accEmail, String accPassword) {
		//Create tempAccount testing our varables
		if(AccountManager.emailValidation(accEmail) == false) {
			userMessage = "Email is incorrect";
			return new AccountRequestResponse(false, userMessage,
					 "login.html");
		}else {
			
			boolean tempAccount = SilverstyleAppApplication.instanceOfAccountPersistanceManager().loginToAccount(accEmail,accPassword);
			//tempAccount is completely false
			if(tempAccount == false) {
				userMessage = "Account not found";
				return new AccountRequestResponse(false, userMessage, "login.html");
			}
			//tempAccount is not false, return true.
			else {
		    	userMessage = "User has been successfully logged in";
					return new AccountRequestResponse(true, userMessage, "accProfile.html");
		    }
		}
	}// end of loginToAccount


	//@TODO: not yet implemented
	public  AccountRequestResponse deleteAccount(AccountRequest registerRequest) {
		//@TODO:
		//valid account?
		//remove account
		return null;
	}//end of deleteAccount

	//Call to buildNewAccount, returns a newAccount from accountRequest
	public Account buildNewAccount(AccountRequest accountRequest ) {
		Account newAccount = new Account();
		numID++;
		newAccount.setAccountID(numID);
		newAccount.setAccPassword(accountRequest.getAccPassword());
		newAccount.setAccFirstName(accountRequest.getAccFirstName());
		newAccount.setAccLastName(accountRequest.getAccLastName());
		newAccount.setAccEmail(accountRequest.getAccEmail());
		newAccount.setAccPhoneNumber(accountRequest.getAccPhoneNumber());
		newAccount.setAccStreetNumber(accountRequest.getAccStreetNumber());
		newAccount.setAccStreetName(accountRequest.getAccStreetName());
		newAccount.setAccCity(accountRequest.getAccCity());
		newAccount.setAccProvince(accountRequest.getAccProvince());
		newAccount.setAccPostalCode(accountRequest.getAccPostalCode());
		return newAccount;
	}//end of buildNewAccount

	//matches email to pattern, returns message.
	public static boolean emailValidation(String email) {
		String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		//String email = accountRequest.getAccEmail(accountRequest);
		if(!email.matches(pattern)) {
			userMessage = "Email format is invalid";
			return false;
		} else {
			return true;
		}
	}//end of emailValidation

	//validate input from field
	//change css to format province
	public static boolean validateProvince(String province) {
			String[] provincesAbbreviations;
			provincesAbbreviations = new String[]{"AB","BC","MB","NB","NL","NT","NS","NU","ON","PE","QC","SK","YT"};
			//String userProvince = accountRequest.getAccProvince();
			
			for(int x=0;x<=provincesAbbreviations.length; x++) {
				
				if(!province.matches(provincesAbbreviations[x])) {
					userMessage = "You must have a Canadian province";
					return true;
				}
				
				// end of else
			}//end of for
			return false;
	}//end of validateInput

	//validate phone number
	public static boolean validatePhone(String phone) {
			String pattern = "^(\\\\d{3}[- .]?){2}\\\\d{4}$";
			//String pattern = "";
			//String userPhone = accountRequest.getAccPhoneNumber();
			if(phone.matches(pattern)) {
				userMessage = "Phone number must be in format (XXX) XXX-XXXX";
				return false;
			} else {
				return true;
			}
	}// end of validatePhone
} // end of accountManager
