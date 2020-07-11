package com.example.instaparse.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instaparse.LoginActivity;
import com.example.instaparse.MainActivity;
import com.example.instaparse.Post;
import com.example.instaparse.PostsAdapter;
import com.example.instaparse.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";

    //member variables
    private RecyclerView rvProfilePosts;
    protected List<Post> allPosts;
    private Button btnLogout;

    //introduce post adapter class
    protected PostsAdapter adapter;

    public ProfileFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //reference to the member variable

        rvProfilePosts = view.findViewById(R.id.rvProfilePosts);
        btnLogout = view.findViewById(R.id.btnLogout);

        //onClickListener on the logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View view) {
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
             Intent i = new Intent(getActivity(), LoginActivity.class);
            i.putExtra("logout", currentUser);


            startActivity(i);

          }
        });

        //allPosts is the data source
        //initialize allPosts to be a new empty List
        allPosts = new ArrayList<>();
        //pass in context and list into our adapter
        adapter = new PostsAdapter(getContext(), allPosts);

        //Steps to use and populate the recycler view
        //0. Create layout for one row in the list
        //1. Create the adapter
        //2. Create the data source
        //3. set the adapter on the recycler view
        rvProfilePosts.setAdapter(adapter);
        //4. set the layout manager on the recycler view
        //by default we will have a vertical linear layout manager provided to the recycler view
        rvProfilePosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }


    protected void queryPosts() {
//        super.queryPosts();
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        //for author of post
        query.include(Post.KEY_USER);
        //user and user's attributes are equal to each other
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        //limiting the number of objects we get back
        query.setLimit(20);
        //for most recent posts at the top
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
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
                //when we get data back from the  call to queryPosts(), update data source
                allPosts.addAll(posts);
                //notify adapter that we have got new data
                adapter.notifyDataSetChanged();
            }
        });
    }
}
