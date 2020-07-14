package com.example.instaparse;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    //member variables

    Post post;
    TextView tvTimeStamp;
    TextView tvCaption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvCaption = findViewById(R.id.tvCaption);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));;

        tvCaption.setText(post.getDescription());

//        tvTimeStamp = (TextView) findViewById(R.id.tvTimeStamp);
//        tvTimeStamp.setText(post.get());
    }
}