package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {

//    we create variables to replicate the contents of the json

    private int userId;

    private int id;

    private String title;


//    in case the variable name and the json key differ,
//    we create an annotation and pass in the part of the json which it belongs to (in the case "body")
    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
