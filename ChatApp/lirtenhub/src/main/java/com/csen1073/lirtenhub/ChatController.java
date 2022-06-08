package com.csen1073.lirtenhub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@RestController
public class ChatController {
	@Autowired
	FirebaseInitialize db;
	HashMap<String, ListenerRegistration> listenerRegistrationMap = new HashMap<String, ListenerRegistration>();
	
	public void printMessages(List<QueryDocumentSnapshot> messages) {
		try {
			for(DocumentSnapshot doc: messages) {
				ChatMessage cm = doc.toObject(ChatMessage.class);
				System.out.print("[" + cm.getMessageID() + "," + cm.getMessageText() + "," + cm.getMessageUser() + "," + cm.getMessageTime() + "]" + ";");
			}
			System.out.println();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ListenerStart(CollectionReference colRef) {
		//ListenerRegistration lg = yourDocumentRef.addSnapshotListener(YourActivity.this, eventListener);
		listenerRegistrationMap.put(colRef.getId(), colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {

			@Override
			public void onEvent(QuerySnapshot value, FirestoreException error) {
			    if (error != null) {
			        System.err.println("Listen failed: " + error);
			        return;
			      }

			      if (value != null && !value.isEmpty()) {
			        //System.out.println("Current data: " + value.getDocuments());
			    	  //getMessages(colRef.getId());
			      } else {
			        //System.out.print("Current data: null");
			    	  //getMessages(colRef.getId());
			      }
			      System.out.println(colRef.getId() + " messages: ");
			      printMessages(value.getDocuments());
			}
		}));
		
	}
	
	@GetMapping("/getMessages/close/{user}")
	public void ListenerStop(@PathVariable("user") String user) {
		CollectionReference colRef = db.getFirebase().collection(user);
		ListenerRegistration listenerRegistration = listenerRegistrationMap.get(colRef.getId());
	    if (listenerRegistration != null) {
	    	listenerRegistrationMap.get(colRef.getId()).remove();
	        System.out.println(colRef.getId() + " listener closed");
	        listenerRegistration = null;
	        listenerRegistrationMap.put(colRef.getId(), listenerRegistration);
	    }
	}
	
	@GetMapping("/getMessages/{user}")
	public ArrayList<ChatMessage> getMessages(@PathVariable("user") String user) {
		ArrayList<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
		CollectionReference chatMessageCR = db.getFirebase().collection(user);
		ApiFuture<QuerySnapshot> querySnapshot = chatMessageCR.get();
		try {
			for(DocumentSnapshot doc: querySnapshot.get().getDocuments()) {
				ChatMessage cm = doc.toObject(ChatMessage.class);
				chatMessageList.add(cm);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(listenerRegistrationMap.get(chatMessageCR.getId()) == null) {
			ListenerStart(chatMessageCR);
			System.out.println(chatMessageCR.getId() + " listener started");
		}
		
		return chatMessageList;
	}
	
	@PostMapping("/sendMessage/{user}")
	public int saveMessage(@RequestBody ChatMessage message, @PathVariable("user") String user) {
		CollectionReference chatMessageCR = db.getFirebase().collection(user);
        SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        message.setMessageTime(ISO_8601_FORMAT.format(new Date()));
		chatMessageCR.document(String.valueOf(message.getMessageID())).set(message);
		return message.getMessageID();
	}
	
}
