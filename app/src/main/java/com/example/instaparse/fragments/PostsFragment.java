package com.example.instaparse.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instaparse.Post;
import com.example.instaparse.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";

    //member variable
    private RecyclerView rvPosts;

    public PostsFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //reference to the member variable
        rvPosts = view.findViewById(R.id.rvPosts);

        //Steps to use and populate the recycler view
        //0. Create layout for one row in the list
        //1. Create the adapter
        //2. Create the data source
        //3. set the adapter on the recycler view
        //4. set the layout manager on the recycler view
    }

    //invoke method to take posts we have and hand it over to the adapter
    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        //for author of post
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            //get a list of all post objects
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                //iterate through all of the posts
                for(Post post : posts) {
                    Log.i(TAG, "Post:" + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
            }
        });
    }
}