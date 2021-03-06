package com.tau.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {
	
	private int messageID;
    private String messageText;
    private String messageUser;
    private String messageTime;
 
    public ChatMessage(int messageID, String messageText, String messageUser) {
    	this.messageID = messageID;
    	
        this.messageText = messageText;
        this.messageUser = messageUser;
 
        SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        messageTime = ISO_8601_FORMAT.format(new Date());
    }
 
    public ChatMessage() {
 
    }
    
    public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getMessageText() {
        return messageText;
    }
 
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
 
    public String getMessageUser() {
        return messageUser;
    }
 
    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }
 
    public String getMessageTime() {
        return messageTime;
    }
 
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
    
}
