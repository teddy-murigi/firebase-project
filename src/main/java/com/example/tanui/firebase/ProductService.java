package com.example.tanui.firebase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class ProductService {

    public static final String COLLECTION_NAME="products";

    public String saveProductDetails(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Product getProductDetailsByName(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Product product = null;

        if(document.exists()) {
            product = document.toObject(Product.class);
            return product;
        }else {
            return null;
        }
    }

    public String updateProductDetails(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteProduct(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(name).delete();
        return "Document with Product ID "+name+" has been deleted";
    }

   

}
