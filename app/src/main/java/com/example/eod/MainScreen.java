package com.example.eod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eod.daos.PostDao;
import com.example.eod.daos.UserDao;
import com.example.eod.models.Post;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

public class MainScreen extends AppCompatActivity {
    Button logoutButt;
    FloatingActionButton addQuote;
    TextView welcomeTxt,welcome;
    PostAdapter adapter;
    RecyclerView recyclerView;
    PostDao postDao;

    private FirebaseAuth auth = FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        logoutButt = findViewById(R.id.logoutButton);
        addQuote = findViewById(R.id.quote);
        welcome = findViewById(R.id.welcome);

        welcome.setText("Welcome "+auth.getCurrentUser().getDisplayName()+"!");
        addQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCreatePost = new Intent(MainScreen.this,CreatePost.class);
                startActivity(toCreatePost);
            }
        });
        logoutButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(MainScreen.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainScreen.this,"User Sigined Out",Toast.LENGTH_SHORT).show();

                        Intent toSignUp = new Intent(MainScreen.this,SignUp.class);
                        startActivity(toSignUp);
                    }
                });
            }
        });
        setUpRecyclerView();
    }

    public void setUpRecyclerView() {
        postDao = new PostDao();
        CollectionReference postCollection = postDao.postCollection;
        Query query = postCollection.orderBy("createdAt",Query.Direction.DESCENDING);
        Log.d("query", "setUpRecyclerView: "+query);
        recyclerView =findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);

        FirestoreRecyclerOptions<Post> recyclerOptions = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query,Post.class).build();
        adapter = new PostAdapter(recyclerOptions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        Log.d("RcOpts", "setUpRecyclerView: "+recyclerOptions);

        Log.d("adap", "setUpRecyclerView: "+adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}