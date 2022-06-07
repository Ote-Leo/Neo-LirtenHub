package com.tau.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;

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
    
    @Async("asyncExecutor")

    public ChatMessage() {
 
    }
    
    @Async("asyncExecutor")
    public int getMessageID() {
		return messageID;
	}
    
    @Async("asyncExecutor")
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

    @Async("asyncExecutor")
	public String getMessageText() {
        return messageText;
    }
 
    @Async("asyncExecutor")
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
 
    @Async("asyncExecutor")
    public String getMessageUser() {
        return messageUser;
    }
 
    @Async("asyncExecutor")
    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }
 
    @Async("asyncExecutor")
    public String getMessageTime() {
        return messageTime;
    }
 
    @Async("asyncExecutor")
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
    
}
