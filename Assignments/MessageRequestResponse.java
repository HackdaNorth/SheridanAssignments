// @Project Manager Sam github.com/samm-w
// @author Mason github.com/pellemas
// @author Connor  github.com/HackdaNorth
// @author Taranpreet github.com/kaur6499

package com.VOICE.VOICESOFTWARE.DTO;

import java.util.List;
import com.VOICE.VOICESOFTWARE.DomainObjects.Account;
import com.VOICE.VOICESOFTWARE.DomainObjects.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class MessageRequestResponse {

	private List<Message> messageListResult;
	private List<Message> senderList;
	private List<Message> receiverList;
	private Account receiverAccount;
	private Account senderAccount;
	private Account AuthUser;
	public String senderName;
	public String receiverName;
	private Boolean success;
	private String htmlPage;
	private String messageStatus;
	private String messageContent;
	private String chatRoomID;
	private String message;
	private MessageRequest messageResult;
	private MessageRequest messageRequest;
	private String returnMessageToUser;
	private List<Message> conversations;
	
	public MessageRequestResponse(List<Message> messageListResult, Boolean success, String htmlPage) {
		this.messageListResult = messageListResult;
		this.success = success;
		this.htmlPage = htmlPage;

	}

	public MessageRequestResponse(List<Message> messageListResult,String chatRoomID, Boolean success, String htmlPage) {
		this.messageListResult = messageListResult;
		this.chatRoomID = chatRoomID;
		this.success = success;
		this.htmlPage = htmlPage;

	}
	public MessageRequestResponse(List<Message> tempMessage,String chatRoomID, Account recAccount, Account authUser ,String message ,String htmlPage) {
		this.conversations = tempMessage;
		this.receiverAccount = recAccount;
		this.AuthUser =  authUser;
		this.returnMessageToUser = message;
		this.chatRoomID = chatRoomID;
		this.htmlPage = htmlPage;

	}
	public MessageRequestResponse(List<Message> tempMessage,String chatRoomID, String message ,String htmlPage) {
		this.conversations = tempMessage;
		this.returnMessageToUser = message;
		this.chatRoomID = chatRoomID;
		this.htmlPage = htmlPage;

	}
	public MessageRequestResponse(Account receiverAccount, Account senderAccount,  boolean condition, String returnMessageToUser , String url) {
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.returnMessageToUser = returnMessageToUser;
		this.success = condition;
		this.htmlPage = url;
	}
	public MessageRequestResponse(Account receiverAccount, Account senderAccount, List<Message> messageListResult, boolean condition, String returnMessageToUser , String url) {
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.returnMessageToUser = returnMessageToUser;
		this.messageListResult = messageListResult;
		this.success = condition;
		this.htmlPage = url;
	}
	public MessageRequestResponse(Account receiverAccount, Account senderAccount, List<Message> messageListResult ,String chatRoomID ,boolean condition,  String url) {
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.messageListResult = messageListResult;
		this.chatRoomID = chatRoomID;
		this.success = condition;
		this.htmlPage = url;
	}
	public MessageRequestResponse(Account receiverAccount, Account senderAccount,  String chatRoomID, boolean condition,  String url) {
		this.chatRoomID = chatRoomID;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		
		this.success = condition;
		this.htmlPage = url;
	}

    public MessageRequestResponse(String content) {
        this.messageContent = content;
    }
	public List<Message> getAllMessages() {
		return messageListResult;
	}
	public boolean storeMessages(List<Message> messageList) {
		return success;
	}
	public Account getReceiverAccount() {
		return receiverAccount;
	}
	public Account getSenderAccount() {
		return senderAccount;
	}
	public List<Message> getMessageListResult() {
		return messageListResult;
	}
	public List<Message> getConversations() {
		return conversations;
	}
	public String getMessage() {
		return returnMessageToUser;
	}
	public Account getReceiver() {
		return receiverAccount;
	}
	public Account getAuth() {
		return AuthUser;
	}
}
