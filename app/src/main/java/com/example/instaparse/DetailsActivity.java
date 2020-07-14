package com.example.instaparse;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        tvTimeStamp = findViewById(R.id.tvTimeStamp);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));;

        tvCaption.setText(post.getDescription());

        Date date = post.getCreatedAt();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMM yyyy");
        String stringDate = DateFor.format(date);
        tvTimeStamp.setText(stringDate);

    }
}