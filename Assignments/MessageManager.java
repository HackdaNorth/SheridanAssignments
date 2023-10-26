// @Project Manager Sam github.com/samm-w
// @author Mason github.com/pellemas
// @author Connor  github.com/HackdaNorth
// @author Taranpreet github.com/kaur6499

package com.VOICE.VOICESOFTWARE.UseCaseManagers;



import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import com.VOICE.VOICESOFTWARE.VoiceSoftware;
import com.VOICE.VOICESOFTWARE.DTO.MessageRequestResponse;
import com.VOICE.VOICESOFTWARE.DomainObjects.Account;
import com.VOICE.VOICESOFTWARE.DomainObjects.Message;
import com.VOICE.VOICESOFTWARE.Repositories.AccountRepository;
import com.VOICE.VOICESOFTWARE.Repositories.MessageRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class MessageManager {

	private String chatRoomID;
	private String userMessage;

	
	//Add functionality too send the typed message using MessagePersistanceManager
	public MessageRequestResponse sendMessage(MessageRepository msgRepo, Authentication auth, AccountRepository accRepo, String directMessage, String receiverAccountEmail, String senderAccountEmail) {
		Account receiverAccount = accRepo.findByAccEmail(receiverAccountEmail);	
		Account senderAccount = accRepo.findByAccEmail(senderAccountEmail);
		Account authenticatedUser = accRepo.findByAccEmail(auth.getName());
		
		if(directMessage == null || directMessage.isEmpty()) {
			return new MessageRequestResponse(receiverAccount, senderAccount, false, "Message cannot be blank" ,"sendDirectMessage.html" );
		} else {
			Message tempMessage = VoiceSoftware.instanceOfMessagePersistanceManager().sendMessage(msgRepo, auth, accRepo, directMessage, receiverAccount, senderAccount);
			List<Message> messageList = new ArrayList<Message>();
			messageList.add(tempMessage);
			VoiceSoftware.instanceOfMessagePersistanceManager().storeMessages(msgRepo, messageList );
			messageList.clear();
			List <Message> tempList = msgRepo.findBychatRoomID(tempMessage.getChatRoomID());
			
			for (Message message : tempList ) {
				if(message.getSenderAccountID() == authenticatedUser.getAccountID() && message.getSenderMessageStatus() == 1) {
					messageList.add(message);
				} else if(message.getReceiverAccountID() == authenticatedUser.getAccountID() && message.getReceiverMessageStatus() == 1) {
					messageList.add(message);
				}
			}
			return new MessageRequestResponse(receiverAccount, senderAccount, messageList , true, null ,"sendDirectMessage.html" );	
		}
	}
	public MessageRequestResponse buildNewChat(Account viewedAccount, Authentication auth, AccountRepository accRepo, MessageRepository msgRepo) {
		String localChatRoomID;
		int senderAccountID;
		int receiverAccountID;
		Account receiverAccount = viewedAccount;
		Account senderAccount = accRepo.findByAccEmail(auth.getName());
		receiverAccountID =  receiverAccount.getAccountID();
		senderAccountID =  senderAccount.getAccountID();
		if(senderAccountID > receiverAccountID) { 
			localChatRoomID =  String.valueOf(senderAccountID) + ',' + String.valueOf(receiverAccountID);
		}else {
			localChatRoomID = String.valueOf(receiverAccountID)+ ',' + String.valueOf(senderAccountID); 
		}
		if(!msgRepo.findBychatRoomID(localChatRoomID).isEmpty()) {
			List<Message> messageList = new ArrayList<Message>();
			messageList.addAll(msgRepo.findBychatRoomID(localChatRoomID));
			for(Message message : messageList) {
				localChatRoomID = message.getChatRoomID();
				return new MessageRequestResponse(receiverAccount, senderAccount ,localChatRoomID, true, "sendDirectMessage.html" );
			}	
		}
		return new MessageRequestResponse(receiverAccount, senderAccount,  localChatRoomID, true, "sendDirectMessage.html" );
	}
	//Add functionality to store all messages into MessagePersistanceDispatcher for repository communication return boolean for sendMessage
	public boolean storeMessages(MessageRepository msgRepo, List<Message> messageList) {
		boolean result = VoiceSoftware.instanceOfMessagePersistanceManager().storeMessages(msgRepo,messageList);
		return result;
	}
	public MessageRequestResponse getChatRooms(MessageRepository msgRepo, AccountRepository accRepo ,Authentication auth) {

		Account authenticatedUser = accRepo.findByAccEmail(auth.getName());
	
		List<Message> messageList = new ArrayList<Message>();

		messageList.addAll(msgRepo.findByReceiverAccountIDAndReceiverMessageStatus(authenticatedUser.getAccountID(), 1));
		messageList.addAll(msgRepo.findBySenderAccountIDAndSenderMessageStatus(authenticatedUser.getAccountID(), 1));
		
		String recAccountUsername = "";
		Account recAccount;

		List<String> chatIDList = new ArrayList<String>();
		String chatRoomID = "";
		for(Message message : messageList) {	
			recAccountUsername = message.getReceiverUsername();
			//something in this method needs to be fixed
			if(message.getSenderAccountID() == authenticatedUser.getAccountID() && message.getSenderMessageStatus() == 1) {
			chatIDList.add(message.getChatRoomID());
			chatRoomID = message.getChatRoomID();
			} else if (message.getReceiverAccountID() == authenticatedUser.getAccountID() && message.getReceiverMessageStatus() == 1) {
				chatIDList.add(message.getChatRoomID());
				chatRoomID = message.getChatRoomID();
				}
		}
		List<Message> chatRoomList = VoiceSoftware.instanceOfMessagePersistanceManager().getChatRooms(msgRepo, auth, chatIDList);
		if(chatRoomList.isEmpty()) {
			userMessage = "Message a parent to get started";
			return new MessageRequestResponse(chatRoomList, chatRoomID, userMessage, "viewMessages.html");
		} else {
			userMessage = null;
		}
		recAccount = accRepo.findByAccEmail(recAccountUsername);
		
	
		return new MessageRequestResponse(chatRoomList, chatRoomID, recAccount, authenticatedUser ,userMessage, "viewMessages.html");
	}
	//Add functionality to grab all Messages from repository MessagePersistanceManager / Dispatcher
	public MessageRequestResponse getAllMessages(MessageRepository msgRepo, Authentication auth, AccountRepository accountRepo, String chatRoomID) {

		List<Message> messageList = VoiceSoftware.instanceOfMessagePersistanceManager().getAllMessages(msgRepo, chatRoomID, auth, accountRepo);
		String receiverUsername = "";
		for(Message message : messageList) {
			receiverUsername = message.getReceiverUsername();
			if(receiverUsername == auth.getName()) {
				receiverUsername = message.getSenderUsername();	
			}
		}
		Account senderAccount = accountRepo.findByAccEmail(auth.getName());
		Account receiverAccount = accountRepo.findByAccEmail(receiverUsername);
		return new MessageRequestResponse(receiverAccount,senderAccount, messageList, chatRoomID, true, "sendDirectMessage.html");
	}
	public MessageRequestResponse deleteMessage(MessageRepository msgRepo, AccountRepository accRepo, Authentication auth, String currentChatRoomID) {
		
		List<Message> messageList = new ArrayList<Message>();
		messageList.addAll(msgRepo.findBychatRoomID(currentChatRoomID));
		List<String> chatIDList = new ArrayList<String>();
		String chatRoomID = "";
		for(Message message : messageList) {
				chatIDList.add(message.getChatRoomID());
				chatRoomID = message.getChatRoomID();
				if (auth.getName().equals(message.getSenderUsername())) {
					message.setSenderMessageStatus(0);
					} else {
						message.setReceiverMessageStatus(0);
					}
				msgRepo.save(message);
		}
		List<Message> chatRoomList = VoiceSoftware.instanceOfMessagePersistanceManager().getChatRooms(msgRepo, auth, chatIDList);
		if(chatRoomList.isEmpty()) {
			userMessage = "Message a parent to get started";
			return new MessageRequestResponse(chatRoomList, chatRoomID, userMessage, "viewMessages.html");
		} else {
			userMessage = " ";
		}
		return new MessageRequestResponse(chatRoomList, chatRoomID, userMessage, "viewMessages.html");
	}
}
