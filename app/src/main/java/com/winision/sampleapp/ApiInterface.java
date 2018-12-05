package com.winision.sampleapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//https://app.xrassist.com:8090/users
//https://jsonplaceholder.typicode.com/posts
public interface ApiInterface {
    @GET("users/")
    Call <List<Modal>> getUserData();
}
