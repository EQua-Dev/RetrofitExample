package com.example.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

//    we then tell the retrofit where to actually execute the retrofit annotation command which is the relative url
    @GET("posts")

//    we create a method that will call the list contents of the Post class
//    all we do is to declare the method and the retrofit will auto generate the required codes to fetch the data
    Call<List<Post>> getPost();
}
