package com.tau.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@RestController
public class ChatController {
	@Autowired
	FirebaseInitialize db;
	HashMap<String, ListenerRegistration> listenerRegistrationMap = new HashMap<String, ListenerRegistration>();
	
	@Autowired
	FirebaseInitialize bucket;
	
	
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
	
	@GetMapping("/getMessages/close/{usersID}")
	@Cacheable(value = "messages", key = "#usersID")
	public String ListenerStop(@PathVariable("usersID") String usersID) {
		CollectionReference colRef = db.getFirebase().collection(usersID);
		ListenerRegistration listenerRegistration = listenerRegistrationMap.get(colRef.getId());
	    if (listenerRegistration != null) {
	    	listenerRegistrationMap.get(colRef.getId()).remove();
	        System.out.println(colRef.getId() + " listener closed");
	        listenerRegistration = null;
	        listenerRegistrationMap.put(colRef.getId(), listenerRegistration);
	        return colRef.getId() + " listener closed";
	    }
	    return null;
	}
	
	@GetMapping("/getMessages/{usersID}")
	@Cacheable(value = "messages", key = "#usersID")
	public ArrayList<ChatMessage> getMessages(@PathVariable("usersID") String usersID) {
		ArrayList<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
		CollectionReference chatMessageCR = db.getFirebase().collection(usersID);
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
	
	@PostMapping("/sendMessage/{usersID}")
	@Cacheable(value = "messages", key = "#usersID")
	public int saveMessage(@RequestBody ChatMessage message, @PathVariable("usersID") String usersID) {
		CollectionReference chatMessageCR = db.getFirebase().collection(usersID);
        SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        message.setMessageTime(ISO_8601_FORMAT.format(new Date()));
		chatMessageCR.document(String.valueOf(message.getMessageID())).set(message);
		return message.getMessageID();
	}
	
	//public static void uploadObject(String projectId, String bucketName, String objectName, String filePath) throws IOException
	@PostMapping("/sendImage/{usersID}")
	@Cacheable(value = "messages", key = "#usersID")
	public void uploadObject(@RequestBody ChatMessage message, @PathVariable("usersID") String usersID) throws IOException {
		    // The ID of your GCP project
		    // String projectId = "your-project-id";
			
			
		    // The ID of your GCS bucket
		    // String bucketName = "your-unique-bucket-name";
			//lirtenhub-74ddf.appspot.com
			String bucketName = "lirtenhub-74ddf.appspot.com";

		    // The ID of your GCS object
		    // String objectName = "your-object-name";
			//String objectName = "Image1";
			String objectName = usersID + "_" + message.getMessageID()+"";
			
			System.out.println(bucket.getBucket().getName());
			Storage storage = bucket.getBucket().getStorage();
		    // The path to your file to upload
		    // String filePath = "path/to/your/file"
			//String filePath = "C:\\Users\\amirw\\Documents\\Demo.png";
			String filePath = message.getMessageText();
			
		    //Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		   
		    BlobId blobId = BlobId.of(bucketName, objectName);
		    
		    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		    
		    storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

		    
		    System.out.println(
		        "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
		  }
	
	//public void downloadObject(String projectId, String bucketName, String objectName, String destFilePath)
	@GetMapping("/getImage/{ID}")
	@Cacheable(value = "messages", key = "#ID")
	public void downloadObject(@PathVariable("ID") String usersID) {
		    // The ID of your GCP project
		    // String projectId = "your-project-id";
			

		    // The ID of your GCS bucket
		    // String bucketName = "your-unique-bucket-name";
			String bucketName = "lirtenhub-74ddf.appspot.com";

		    // The ID of your GCS object
		    // String objectName = "your-object-name";
			//String objectName = "Image1";
			String objectName = usersID;
			
		    // The path to which the file should be downloaded
		    // String destFilePath = "/local/path/to/file.txt";
			String destFilePath = "C:\\Users\\Documents\\" + usersID + ".png";

			Storage storage = bucket.getBucket().getStorage();
		    //Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

		    Blob blob = storage.get(BlobId.of(bucketName, objectName));
		    blob.downloadTo(Paths.get(destFilePath));

		    System.out.println(
		        "Downloaded object "
		            + objectName
		            + " from bucket name "
		            + bucketName
		            + " to "
		            + destFilePath);
		  }
	
}
