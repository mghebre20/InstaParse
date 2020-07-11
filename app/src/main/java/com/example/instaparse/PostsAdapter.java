package com.example.instaparse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    //have constructor for post adapter take in two parameters
    //use constructor to fill out each of the methods to override
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> post) {
        this.context = context;
        this.posts = post;
    }

    //methods required for every recycler view adapter
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get layout inflator from context and inflate the item_post layout
        //assign to a view since it returns a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        //wrap the view inside of a view holder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get post located at position and returns a post
        Post post = posts.get(position);
        //bind the data of the post into ViewHolder
        holder.bind(post);
    }

    //length of the posts
    @Override
    public int getItemCount() {
        return posts.size();
    }

    //swipe to refresh
    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> posts) {
        posts.addAll(posts);
        notifyDataSetChanged();
    }

    //define class ViewHolder in order to be parametized
    class ViewHolder extends RecyclerView.ViewHolder {

        //reference to view elements
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //identify the views by id inside of item views passed in
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }

        // bind method
        //bind the post data to the view elements
        //take the data from the post and bind it to the view elements defined in ViewHolder
        public void bind(Post post) {
            //set text, grabbing the description field from the post
            tvDescription.setText(post.getDescription());
            //set text, from user of the post, getting the username
            tvUsername.setText(post.getUser().getUsername());

            //local variable
            ParseFile image = post.getImage();
            //check if post has a valid image
            if(image != null) {
                //use glide library to obtain image view element
                //pass in a context and use the url assigned to the post's image
                // load into imageView
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }
    }
}
