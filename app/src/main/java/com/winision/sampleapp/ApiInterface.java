package com.winision.sampleapp;

import com.winision.sampleapp.Modals.ImagesModal;
import com.winision.sampleapp.Modals.Modal;
import com.winision.sampleapp.Modals.ModelModal;
import com.winision.sampleapp.Modals.NotesModal;
import com.winision.sampleapp.Modals.ProductModal;
import com.winision.sampleapp.Modals.VideoModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//https://app.xrassist.com:8090/users
//https://jsonplaceholder.typicode.com/posts
//https://jsonplaceholder.typicode.com/photos
//https://jsonplaceholder.typicode.com/todos
public interface ApiInterface {
    @GET("users/")
    Call <List<Modal>> getUserData();

    @GET("photos/")
    Call<List<ImagesModal>> getImageData();

    @GET("posts/")
    Call<List<NotesModal>> getNotesData();

    @GET("photos/")
    Call<List<VideoModal>> getVideoData();

    @GET("todos/")
    Call<List<ProductModal>> getProductData();

    @GET("todos/")
    Call<List<ModelModal>> getModelData();

}
