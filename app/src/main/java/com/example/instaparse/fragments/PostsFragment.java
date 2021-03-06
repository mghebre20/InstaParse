package com.example.instaparse.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instaparse.Post;
import com.example.instaparse.PostsAdapter;
import com.example.instaparse.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";

    //member variables

    private RecyclerView rvPosts;
    protected List<Post> allPosts;
    private SwipeRefreshLayout scContainer;

    //introduce post adapter class
    protected PostsAdapter adapter;

    public PostsFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //reference to the member variable

        rvPosts = view.findViewById(R.id.rvPosts);

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
         rvPosts.setAdapter(adapter);
        //4. set the layout manager on the recycler view
        //by default we will have a vertical linear layout manager provided to the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();

        // referencing for the the swipe container view
        scContainer = view.findViewById(R.id.scContainer);
        // Setup refresh listener which triggers new data loading
        scContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateHomePost();
            }
        });

        // Configure the refreshing colors
        scContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void populateHomePost() {
        //clear the adapter
        adapter.clear();
        //populate with new data
        adapter.addAll(allPosts);
        scContainer.setRefreshing(false);
    }

    //invoke method to take posts we have and hand it over to the adapter
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        //for author of post
        query.include(Post.KEY_USER);
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