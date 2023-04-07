package com.example.eod.daos;

import android.util.Log;

import com.example.eod.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDao {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userCollection = db.collection("users");

    public void addUser(User user)
    {
        Log.d("Info","add Info: "+user.uid+" "+user.displayName+" "+user.imageUrl);
        if(user != null)
        {

            userCollection.document(user.uid).set(user);
            Log.d("UserC", "addUser: Checking if running");
        }
    }

    public Task<DocumentSnapshot> getUserById(String uID)
    {
        return userCollection.document(uID).get();

    }



}
