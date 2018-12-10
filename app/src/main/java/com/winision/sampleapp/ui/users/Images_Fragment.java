package com.winision.sampleapp.ui.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winision.sampleapp.ApiInterface;
import com.winision.sampleapp.Adapters.ImageAdapter;
import com.winision.sampleapp.Clients.ImageClient;
import com.winision.sampleapp.Modals.ImagesModal;
import com.winision.sampleapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Images_Fragment extends Fragment {


    private ImageAdapter imageAdapter;

    public Images_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images_, container, false);

        ApiInterface apiClient = ImageClient.getImageClient().create(ApiInterface.class);
        Call<List<ImagesModal>> call = apiClient.getImageData();

        final RecyclerView recyclerView = view.findViewById(R.id.listView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        imageAdapter = new ImageAdapter(this.getActivity());
        recyclerView.setAdapter(imageAdapter);

        call.enqueue(new Callback<List<ImagesModal>>() {
            @Override
            public void onResponse(Call<List<ImagesModal>> call, Response<List<ImagesModal>> response) {
                List<ImagesModal> data = response.body();
                imageAdapter.addImages(data);
            }

            @Override
            public void onFailure(Call<List<ImagesModal>> call, Throwable t) {

            }
        });

        return view;
    }

}
