package com.example.eod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eod.daos.PostDao;

public class CreatePost extends AppCompatActivity {
    Button postButton;
    EditText post;
    private PostDao postDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        postDao = new PostDao();
        postButton = findViewById(R.id.postButt);
        post = findViewById(R.id.postInput);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = post.getText().toString().trim();
                if(input.isEmpty()== false)
                {
                    postDao.addPost(input);
                    finish();
                }
            }
        });
    }
}