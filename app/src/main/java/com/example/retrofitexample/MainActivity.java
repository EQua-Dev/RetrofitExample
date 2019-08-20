package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

//        we then create an instance of retrofit
        Retrofit retrofit = new Retrofit.Builder()
//                we then pass in the base web url address of the web data we want to fetch
                .baseUrl("https://jsonplaceholder.typicode.com/")
//                we indicate that we want to use Gson to pass the response
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        we then create our JsonPlaceHolderApi and summon retrofit to implement our declared method on that class on the mainActivity
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        we use the call object to execute our network request
        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

//        we then execute the call in order to get our response back
//        but instead of '.execute', we use a retrofit provided action called 'enqueue'
//        '.execute' runs the request on the main thread which would freeze the app, thus is not advisable

        call.enqueue(new Callback<List<Post>>() {
            @Override
//            onResponse is for when we get a response from the server whether the right answer or a html code... any response at all
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                 we check if the the response we received is not the required information
                if (!response.isSuccessful()){
//                    we set the textView to display the http code
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

//                we assign the response we will get to the content of the variables in the Post class
                List<Post> posts = response.body();

//                we then append each content of the post network request to certain defined variables in the Post class
                for (Post post : posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                textViewResult.setText(throwable.getMessage());
            }
        });
    }
}
