package com.example.eod;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eod.models.Post;
import com.example.eod.Utils;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostAdapter extends FirestoreRecyclerAdapter<Post, PostAdapter.PostViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PostAdapter(@NonNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }



    @SuppressLint("ResourceType")
    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull Post model) {
        holder.postText.setText(model.getText());
        Log.d("posting", "onBindViewHolder: "+holder.postText.getText());
        holder.userName.setText(model.createdBy.displayName);
        Log.d("posting1", "onBindViewHolder: Hello Binding");
        if(model.likedBy != null)
        {
            holder.likeCount.setText(model.likedBy.size());
        }
        if(holder.userImage != null)
        {

            holder.userImage.setImageDrawable(ContextCompat.getDrawable(holder.userImage.getContext(),R.drawable.gamer));
            Log.d("userImg", "onBindViewHolder: UserImageRunning"+holder.userImage);
        }
        else
        {
            Glide.with(holder.userImage.getContext()).load(model.createdBy.imageUrl).circleCrop().into(holder.userImage);


        }

        holder.createdAT.setText(Utils.getTimeAgo(model.createdAt));

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostViewHolder viewHolder = new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false));
        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }




    class PostViewHolder extends RecyclerView.ViewHolder
   {
        TextView postText;
        TextView userName;
        TextView createdAT;
        TextView likeCount;
        ImageView userImage;
        ImageView likeButton;


       public PostViewHolder(@NonNull View itemView) {
           super(itemView);

           postText = itemView.findViewById(R.id.postTitle);
           userName = itemView.findViewById(R.id.userName);
           createdAT = itemView.findViewById(R.id.createdAt);
           likeCount = itemView.findViewById(R.id.likeCount);
           userImage = itemView.findViewById(R.id.userImage);
           likeButton = itemView.findViewById(R.id.likeButton);

       }
   }
interface IpostAdapter
{
    public void onLikeClicked(String postId);

}

}

