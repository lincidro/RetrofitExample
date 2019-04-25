package com.roll.retrofitexample.service;

import com.roll.retrofitexample.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface IRetrofit {
    @GET("posts")
    Call<List<Post>> getPosts();
}
