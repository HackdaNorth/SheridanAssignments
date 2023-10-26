// @Project Manager Sam github.com/samm-w
// @author Mason github.com/pellemas
// @author Connor  github.com/HackdaNorth

package com.VOICE.VOICESOFTWARE.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//Springboot imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//project imports
import com.VOICE.VOICESOFTWARE.VoiceSoftware;
import com.VOICE.VOICESOFTWARE.DTO.AccountRequest;
import com.VOICE.VOICESOFTWARE.DTO.AccountRequestResponse;
import com.VOICE.VOICESOFTWARE.DTO.MessageRequestResponse;
import com.VOICE.VOICESOFTWARE.DomainObjects.Account;
import com.VOICE.VOICESOFTWARE.Repositories.*;
import com.VOICE.VOICESOFTWARE.Repositories.AccountRepository;
import com.VOICE.VOICESOFTWARE.Repositories.MessageRepository;

//Start of VOICEDispatcher & Controllers
@Controller
public class VOICEDispatcher {
	
	@Autowired
	@Lazy
	private AccountRepository accountRepo;
	@Autowired
	@Lazy
	private MessageRepository messageRepo;
	@Autowired
	@Lazy
	private RoleRepository roleRepo;
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	@GetMapping("/")
	public String landingPage() {
		return ("index");
	}
	//Logging Page Mapping
	@RequestMapping("/viewFAQ")
	public String viewFAQ() {
		return ("faq");
	}
	//Create Account Page mapping
	@RequestMapping("/createAccount")
	public String createAccount() {	
		return ("createAccount");
	}
	//Tell Spring to watch for /createAccount, and when it is called take form attributes from HTML using Thymeleaf.
	@PostMapping("/createAccount")
	public String createAccount(Model model, @ModelAttribute AccountRequest accountRequest) {
		//Create a new instance of AccountManager for accountCreation, passing the entire Account Object inside that AccountRequest
		AccountRequestResponse requestResponse = VoiceSoftware.instanceOfAccountManager().createAccount(accountRepo, roleRepo , accountRequest);
		// Add the response, and the message variables into Thymeleafs model attribute
		model.addAttribute("account", requestResponse.getAccount());
		//Use getReturnMessageToUser, for specific userMessage
		model.addAttribute("errorMessage", requestResponse.getReturnMessageToUser());
		//return the correct HTML page from accountManager
		return requestResponse.getHtmlPage();
	}
	//Create Account Page mapping
	@RequestMapping("/changeAccount")
	public String changeAccount(Model model, Authentication auth) {
		Account authUser = accountRepo.findByAccEmail(auth.getName());
		model.addAttribute("account", authUser);
		return ("changeAccount");
	}
 
	@PostMapping("/changeAccount")
	public String changeAccount(Model model,Authentication auth  , @ModelAttribute AccountRequest accountRequest) {
		//Create a new instance of AccountManager for account modification, passing the entire Account Object inside that AccountRequest
		AccountRequestResponse requestResponse = VoiceSoftware.instanceOfAccountManager().changeAccount(auth, accountRepo, roleRepo , accountRequest);
		// Add the response, and the message variables into Thymeleafs model attribute
		model.addAttribute("account", requestResponse.getAccount());
		//Use getReturnMessageToUser, for specific userMessage
		model.addAttribute("errorMessage", requestResponse.getReturnMessageToUser());
		//return the correct HTML page from accountManager
		return requestResponse.getHtmlPage();
	}
	//Logging Page Mapping
	@RequestMapping("/login")
	public String loginAccount() {
		return ("login");
	}	
	//Tell Spring to watch for post mapped /login and take login form attributes from HTML
	@PostMapping("/login")
	public String loginToAccount(Model model, @ModelAttribute AccountRequest accountRequest) {
		//Creates a new Instace of AccountManager and calls loginToAccount passing our Requested Parameters
		AccountRequestResponse requestResponse = VoiceSoftware.instanceOfAccountManager().loginToAccount(accountRepo, accountRequest);
		//Add those the whole AccountRequestResponse into a response attribute in Thymeleaf.
		model.addAttribute("account", requestResponse.getAccount());
		//Use getReturnMessageToUser, for specific userMessage
		model.addAttribute("message", requestResponse.getReturnMessageToUser());
		//return the correct HTML page from accountManager
		return requestResponse.getHtmlPage();
	}
	//Get Request mapping for accProfile
	@RequestMapping("/accProfile")
	public String accProfile(Model model, Authentication auth) {
		Account accountRequest = accountRepo.findByAccEmail(auth.getName());
		 //quick patch to stop error out the program, but this returns a null account after clicking on manage account
		model.addAttribute("account", accountRequest);
		return ("accProfile");
	}
	//view account information when clicked on in searchProfiles
	@PostMapping("/resetPass")
	public String resetPass(Model model, Authentication auth, @RequestParam String pwd, @RequestParam String pwdTwo) {
		Account accountRequest = accountRepo.findByAccEmail(auth.getName());
		AccountRequestResponse requestResponse = VoiceSoftware.instanceOfAccountManager().resetPassword(pwd,pwdTwo, auth, accountRepo, roleRepo);
		model.addAttribute("errorMessage", requestResponse.getReturnMessageToUser());
		model.addAttribute("account", accountRequest);
		return requestResponse.getHtmlPage();
	}
	//Get Request Mapping for profileView
	@RequestMapping("/profileView")
	public String profileView() {
		return ("profileView");
	}
	//view account information when clicked on in searchProfiles
	@PostMapping("/profileView")
	public String viewAccount(Model model, @RequestParam Account viewedAccount) {
		AccountRequestResponse selectedAccountResponse = VoiceSoftware.instanceOfAccountManager().viewAccount(viewedAccount);
		model.addAttribute("viewedAccount", viewedAccount);
		model.addAttribute("accChapter", selectedAccountResponse.getMap().get(viewedAccount.getAccCity()));
		model.addAttribute("selectedAccount", selectedAccountResponse.getViewedProfile());
		return selectedAccountResponse.getHtmlPage();
	}
	//Get mapping for searchDisclaimer
	@RequestMapping("/searchDisclaimer")
	public String searchDisclaimer() {
		return ("searchDisclaimer");
	}
	//GET Mappping for /search
	@RequestMapping("/search")
	public String searchProfiles(Model model) {
		AccountRequestResponse listAccounts = VoiceSoftware.instanceOfAccountManager().getAllAccounts(accountRepo);
		model.addAttribute("response", listAccounts.getList());
		model.addAttribute("errorMessage", listAccounts.getReturnMessageToUser());
		return listAccounts.getHtmlPage();
	}
	//POST mapping for once the search/filter button clicked
	@PostMapping("/searchProfiles")
	public String searchProfiles(Model model, @RequestParam String accProvince, @RequestParam String accCity, @RequestParam String schoolBoard, @RequestParam String numOfChildren, @RequestParam String childAge, @RequestParam String typeOfHearing) {
		//make a new instance of AccountManager and call searchFilter, pass the requested variables
		AccountRequestResponse searchResult = VoiceSoftware.instanceOfAccountManager().searchFilter(accountRepo, accProvince, accCity, schoolBoard, numOfChildren, childAge, typeOfHearing);
		//add these variables too the model class. .getList calls the entire list of people. the store in response
		model.addAttribute("response", searchResult.getList());
		model.addAttribute("errorMessage", searchResult.getReturnMessageToUser());
		//AccountRequestResponse has a variable and a function, in that class we give it the proper returned .html
		return searchResult.getHtmlPage();
	}
	//GET viewChatRoom Page Mapping
	@GetMapping("/viewChatRoom")
	public String viewChatRoom(Model model,Authentication auth, @RequestParam String chatRoomID) { //, @RequestParam Account viewedAccount
		MessageRequestResponse messageResponse = VoiceSoftware.instanceOfMessageManager().getAllMessages(messageRepo, auth, accountRepo, chatRoomID);
		//model.addAttribute("viewedAccount", messageResponse.getMessageListResult());
		model.addAttribute("senderAccount", messageResponse.getSenderAccount()); 
		model.addAttribute("receiverAccount", messageResponse.getReceiverAccount());
		model.addAttribute("userMessageHistory", messageResponse.getMessageListResult());
		model.addAttribute("chatRoomID" , messageResponse.getChatRoomID());
		return messageResponse.getHtmlPage();
	}
	@RequestMapping("/deleteMessage")
	public String deleteMessage(Model model, Authentication auth, @RequestParam String chatRoomID) {
		MessageRequestResponse messageResponse = VoiceSoftware.instanceOfMessageManager().deleteMessage(messageRepo, accountRepo, auth, chatRoomID);
		model.addAttribute("senderAccount", messageResponse.getSenderAccount()); 
		model.addAttribute("receiverAccount", messageResponse.getReceiverAccount()); 
		model.addAttribute("userMessageHistory", messageResponse.getMessageListResult());
		model.addAttribute("chatRoomID" , messageResponse.getChatRoomID());
		return messageResponse.getHtmlPage();
	}
	@RequestMapping("/viewMessages")
	public String viewMessages(Model model, Authentication auth) { 

		MessageRequestResponse messageResponse = VoiceSoftware.instanceOfMessageManager().getChatRooms(messageRepo, accountRepo ,auth);
		model.addAttribute("chatRooms", messageResponse.getConversations());
		model.addAttribute("chatRoomID",  messageResponse.getChatRoomID());
		model.addAttribute("receiverAccount", messageResponse.getReceiver());
		model.addAttribute("authUser", messageResponse.getAuth());
		model.addAttribute("errorMessage",  messageResponse.getReturnMessageToUser());
		return messageResponse.getHtmlPage();
	}
	//GET sendDirectMessage Page Mapping
	@RequestMapping("/sendDirectMessage")
	public String sendDirectMessage(Model model, @RequestParam Account viewedAccount, Authentication auth ) {
		MessageRequestResponse messageResponse = VoiceSoftware.instanceOfMessageManager().buildNewChat(viewedAccount, auth, accountRepo, messageRepo);
		model.addAttribute("senderAccount", messageResponse.getSenderAccount());
		model.addAttribute("receiverAccount", messageResponse.getReceiverAccount());
		return messageResponse.getHtmlPage();
	}
	//POST mapping that sends the message to another user.
	@PostMapping("/sendDirectMessage")
	public String sendDirectMessage(Model model,  Authentication auth, @RequestParam String directMessage, @RequestParam String receiverAccountEmail, @RequestParam String senderAccountEmail) { 
		MessageRequestResponse messageResponse = VoiceSoftware.instanceOfMessageManager().sendMessage(messageRepo, auth, accountRepo, directMessage, receiverAccountEmail, senderAccountEmail);
		model.addAttribute("senderAccount", messageResponse.getSenderAccount());
		model.addAttribute("receiverAccount", messageResponse.getReceiverAccount());
		
		model.addAttribute("errorMessage", messageResponse.getMessage());
		model.addAttribute("userMessageHistory", messageResponse.getMessageListResult());
		model.addAttribute("chatRoomID", messageResponse.getChatRoomID());
		return messageResponse.getHtmlPage();
	}

}