package com.winision.sampleapp.ui.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.winision.sampleapp.ApiInterface;
import com.winision.sampleapp.Clients.ProductClient;
import com.winision.sampleapp.Modals.NotesModal;
import com.winision.sampleapp.Modals.ProductModal;
import com.winision.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectProduct extends Fragment {

    private ListView productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_product, container, false);

        productList = view.findViewById(R.id.productList);


        ApiInterface apiClient = ProductClient.getProductClient().create(ApiInterface.class);
        Call<List<NotesModal>> call = apiClient.getNotesData();

        call.enqueue(new Callback<List<NotesModal>>() {
            @Override
            public void onResponse(Call<List<NotesModal>> call, Response<List<NotesModal>> response) {

            }

            @Override
            public void onFailure(Call<List<NotesModal>> call, Throwable t) {

            }
        });
        return view;
    }

}
