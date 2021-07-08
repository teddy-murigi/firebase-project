/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tanui.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEDDY
 */

@Service
public class FirebaseInitializer {
    
    @PostConstruct
    public void initialization(){
        
FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("./serviceAccountKey.json");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FirebaseInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(FirebaseInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

FirebaseApp.initializeApp(options);

    }
}
