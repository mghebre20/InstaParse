package com.example.instaparse;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    //member variables

    Post post;
    TextView tvTimeStamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

//        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));;

        tvTimeStamp = (TextView) findViewById(R.id.tvTimeStamp);



//        tvTimeStamp.setText(post.get());
    }
}