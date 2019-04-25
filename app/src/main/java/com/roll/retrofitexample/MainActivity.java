package com.roll.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.roll.retrofitexample.entity.Post;
import com.roll.retrofitexample.service.IRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text_view_result);


        final String BASE_URL = "https://jsonplaceholder.typicode.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);

        Call<List<Post>> mCall = iRetrofit.getPosts();
        mCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){   //Si el response no es exitoso
                    mTextView.setText("Code: "+response.code());
                    return;
                }

                List<Post> postList = response.body();
                for(Post x:postList){
                    StringBuilder sb = new StringBuilder();
                    sb.append("ID: "+x.getId()+"\n");
                    sb.append("UserId: "+x.getUserId()+"\n");
                    sb.append("Title: "+x.getTitle()+"\n");
                    sb.append("Text: "+x.getText()+"\n\n");

                    mTextView.append(sb);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                mTextView.setText(t.getMessage());
            }
        });

    }
}
