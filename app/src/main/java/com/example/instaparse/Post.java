package com.example.instaparse;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("Post")
public class Post extends ParseObject implements Parcelable {

    //define keys from Parse
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_KEY = "createdAt";

    //GETTER that will return a string
    public String getDescription() {
        //getString is a method from the ParseObject class
        return getString(KEY_DESCRIPTION);
    }

    //SETTER
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    //GETTER
    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    //SETTER
    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    //GETTER
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    //SETTER
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    //GETTER that will return a string
    public String getTimeStamp() {
        //getString is a method from the ParseObject class
        return getString(KEY_CREATED_KEY);
    }

    //SETTER
    public void setTimeStamp(String timeStamp) {
        put(KEY_CREATED_KEY, timeStamp);
    }
}
