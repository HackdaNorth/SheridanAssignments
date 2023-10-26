//@author Connor Murray  github.com/HackdaNorth
package com.webstore.silverstyleApp.DTO;


public class AccountRequest {
	//Ambassador
	//child
	private int accountID;
	private String accPassword;
	private String accFirstName;
	private String accLastName;
	private String accEmail;
	private String accPhoneNumber;
	private String accStreetName;
	private int accStreetNumber;
	private String accCity;
	private String accProvince;
	private String accPostalCode;



	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccFirstName() {
		return accFirstName;
	}
	public void setAccFirstName(String accFirstName) {
		this.accFirstName = accFirstName;
	}
	public String getAccLastName() {
		return accLastName;
	}
	public void setAccLastName(String accLastName) {
		this.accLastName = accLastName;
	}
	public String getAccEmail() {
		return accEmail;
	}
	public void setAccEmail(String accEmail) {
		this.accEmail = accEmail;
	}
	public String getAccPhoneNumber() {
		return accPhoneNumber;
	}
	public void setAccPhoneNumber(String accPhoneNumber) {
		this.accPhoneNumber = accPhoneNumber;
	}
	public String getAccStreetName() {
		return accStreetName;
	}
	public void getAccStreetName(String accStreetname) {
		this.accStreetName = accStreetname;
	}
	public int getAccStreetNumber() {
		return accStreetNumber;
	}
	public void setAccStreetNumber(int accStreetnumber) {
		this.accStreetNumber = accStreetnumber;
	}
	public String getAccCity() {
		return accCity;
	}
	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}
	public String getAccProvince() {
		return accProvince;
	}
	public void setAccProvince(String accProvince) {
		this.accProvince = accProvince;
	}
	public String getAccPostalCode() {
		return accPostalCode;
	}
	public void setAccPostalCode(String accPostalCode) {
		this.accPostalCode = accPostalCode;
	}
	public String getAccPassword() {
		return accPassword;
	}
	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}

}
