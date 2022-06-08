package com.csen1073.lirtenhub;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitialize {

	@PostConstruct
	private void initilaize() {
		try {
			FileInputStream serviceAccount =
					new FileInputStream("./serviceAccount.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://lirtenhub-74ddf-default-rtdb.europe-west1.firebasedatabase.app")
					.build();

			FirebaseApp.initializeApp(options);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
	
}
