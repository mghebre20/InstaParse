package com.example.instaparse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instaparse.fragments.ComposeFragment;
import com.example.instaparse.fragments.PostsFragment;
<<<<<<< HEAD
import com.example.instaparse.fragments.ProfileFragment;
=======
>>>>>>> bdc1ecacc758db3e5d85ee8057beea3f781ce6d0
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

//consists of bottom navigation
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //responsible for changing the fragment that is shown in frame layout
        final FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        //responsible for navigating between activities
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
<<<<<<< HEAD
//                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment= new PostsFragment();
                        break;
                    case R.id.action_compose:
//                        Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
=======
                        //TODO: update fragment
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment= new PostsFragment();
                        break;
                    case R.id.action_compose:
                        Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
>>>>>>> bdc1ecacc758db3e5d85ee8057beea3f781ce6d0
                        fragment= new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
<<<<<<< HEAD
//                        Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        fragment= new ProfileFragment();
=======
                        //TODO: update fragment
                        Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        fragment= new ComposeFragment();
>>>>>>> bdc1ecacc758db3e5d85ee8057beea3f781ce6d0
                        break;

                }
                //make changes based on transactions
                //replacing frame layout with the associated fragment
                // commit, make changes immediately
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        //set a default selection to avoid blank frame layout
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}
