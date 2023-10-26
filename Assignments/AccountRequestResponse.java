// @Project Manager Sam github.com/samm-w
// @author Mason github.com/pellemas
// @author Connor  github.com/HackdaNorth
// @author Taranpreet github.com/kaur6499

package com.VOICE.VOICESOFTWARE.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.VOICE.VOICESOFTWARE.DomainObjects.Account;


public class AccountRequestResponse  {

	// ******************************
	// Public Variables for AccountRequestResponse
	//
	// ******************************
	private boolean success = true; 
	private List<Account> searchResult = new ArrayList<Account>();
	private List<Account> viewedAccount = new ArrayList<Account>();
	private Account loggedInAcc;

	private String returnMessageToUser;
	private String htmlPage;
	private Map<String, String> map;

	// ******************************
	// Public Methods For Account 
	//
	// @AccountRequestResponse(boolean condition, String message, String url)
	// @AccountRequestResponse(boolean condition, List<Account> searchlist, String url)
	// @AccountRequestResponse(Account viewedAccount, String url) 
	// @isSuccess 
	// @setSuccess
	// @getHtmlPage
	// @setHtmlPage
	// @getReturnMessageToUser
	// @setReturnMessageToUser
	// @getList
	// @getViewedProfile
	// ******************************
	
	public AccountRequestResponse( boolean condition, String message, String url) {
	
		this.success = condition;
		this.returnMessageToUser = message;
		this.htmlPage = url;
	}

	public AccountRequestResponse(boolean condition, List<Account> searchlist, String url) {
		this.success = condition;
		this.searchResult = searchlist;
		this.htmlPage = url;
	}
	public AccountRequestResponse(Account viewedAccount,  Map<String, String> map, String url) {
		this.map = map;
		this.viewedAccount.add(viewedAccount);
		this.htmlPage = url;
	}
	public AccountRequestResponse(Account loggedInAcc, boolean condition, String message, String url) {
		this.loggedInAcc = loggedInAcc;
		this.success = condition;
		this.returnMessageToUser = message;
		this.htmlPage = url;
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getHtmlPage() {
		return htmlPage;
	}
	public void setHtmlPage(String htmlPage) {
		this.htmlPage = htmlPage;
	}

	public String getReturnMessageToUser() {
		return returnMessageToUser;
	}
	public void setReturnMessageToUser(String returnMessageToUser) {
		this.returnMessageToUser = returnMessageToUser;
	}
	public List<Account> getList() {

		return searchResult;
	}
	public List<Account> getViewedProfile() {
		
		return viewedAccount;
	}
	public Account getAccount() {
		return loggedInAcc;
	}

}