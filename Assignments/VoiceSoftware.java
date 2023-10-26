// @Project Manager Sam github.com/samm-w
// @author Mason github.com/pellemas
// @author Connor  github.com/HackdaNorth
// @author Taranpreet github.com/kaur6499
package com.VOICE.VOICESOFTWARE;

//Spring boot imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Controller Imports

import com.VOICE.VOICESOFTWARE.Controllers.AccountPersistenceDispatcher;
import com.VOICE.VOICESOFTWARE.Controllers.MessagePersistanceDispatcher;

//UseCaseManagers Imports
import com.VOICE.VOICESOFTWARE.UseCaseManagers.AccountManager;
import com.VOICE.VOICESOFTWARE.UseCaseManagers.AccountPersistenceManager;
import com.VOICE.VOICESOFTWARE.UseCaseManagers.MessageManager;
import com.VOICE.VOICESOFTWARE.UseCaseManagers.MessagePersistanceManager;

import lombok.AllArgsConstructor;


@SpringBootApplication
@AllArgsConstructor
public class VoiceSoftware {

	//Account variables
	private static AccountManager accountManager;
	private static AccountPersistenceManager accountpersistenceManager;
	
	//Message variables
	private static MessageManager messageManager;
	private static MessagePersistanceManager messagePersistenceManager;

	//main
	public static void main(String[] args) {
		// run voice software application
		SpringApplication.run(VoiceSoftware.class, args);
	}
	
	//AccountManager
	public static AccountManager instanceOfAccountManager () {
		//if the account manager doesn't exist make a new one 
		if(accountManager == null) {
			accountManager = new AccountManager();
		}
		// return the new accountManager to VOICEDispatcher
		return accountManager;
	}


	//AccountPersistenceManager
	public static AccountPersistenceManager instanceOfAccountPersistanceManager () {
		// if the AccountPersistanceManager doesn't exist, make a new one
		if(accountpersistenceManager == null) {
			accountpersistenceManager = new AccountPersistenceDispatcher();
		}
		// return the new AccountPersistanceManager to VOICEDispatcher
		return accountpersistenceManager;
	}
	
	
	
	//MessageManager
	public static MessageManager instanceOfMessageManager () {
		//if the account manager doesn't exist make a new one 
		if(messageManager == null) {
			messageManager = new MessageManager();
		}
		// return the new accountManager to VOICEDispatcher
		return messageManager;
	}
	//MessagePersistanceManager
	public static MessagePersistanceManager instanceOfMessagePersistanceManager () {
		// if the AccountPersistanceManager doesn't exist, make a new one
		if(messagePersistenceManager == null) {
			messagePersistenceManager = new MessagePersistanceDispatcher();
		}
		// return the new AccountPersistanceManager to VOICEDispatcher
		return messagePersistenceManager;
	}
}