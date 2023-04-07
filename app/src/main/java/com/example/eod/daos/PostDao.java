package com.example.eod.daos;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.eod.models.Post;
import com.example.eod.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostDao {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public CollectionReference postCollection = db.collection("posts");
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    public void addPost(String text)
    {
        Log.d("textMsg", "addPost: "+text);
            String currentUserID = auth.getCurrentUser().getUid();
        Log.d("currentUid", "addPost: "+currentUserID);
            UserDao userDao = new UserDao();
           userDao.getUserById(currentUserID).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
               @Override
               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   if(task.isSuccessful())
                   {
                        DocumentSnapshot document = task.getResult();
                        if(document != null && document.exists())
                        {
                            User user = document.toObject(User.class);
                            Log.d("userObj", "addPost: "+user);
                            long currentTime = System.currentTimeMillis();

                            Post post = new Post(text,user,currentTime,null);

                            postCollection.document(post.text).set(post);
                            Log.d("checker", "addPost: Checking if running");

                        }
                   }
               }
           });



    }
}
