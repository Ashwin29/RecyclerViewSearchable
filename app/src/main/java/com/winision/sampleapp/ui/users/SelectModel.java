package com.winision.sampleapp.ui.users;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.winision.sampleapp.ApiInterface;
import com.winision.sampleapp.Clients.ModelClient;
import com.winision.sampleapp.Clients.ProductClient;
import com.winision.sampleapp.Modals.ModelModal;
import com.winision.sampleapp.Modals.ProductModal;
import com.winision.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


public class SelectModel extends Fragment {

    private ListView modelListview;
    private ArrayList<String> models = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_model, container, false);

        modelListview = view.findViewById(R.id.modelList);

        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, models);

        modelListview.setAdapter(adapter);

        ApiInterface apiClient = ModelClient.getModelClient().create(ApiInterface.class);
        retrofit2.Call<List<ModelModal>> call = apiClient.getModelData();

        call.enqueue(new Callback<List<ModelModal>>() {
            @Override
            public void onResponse(retrofit2.Call<List<ModelModal>> call, Response<List<ModelModal>> response) {
                List<ModelModal> data = response.body();
                for (ModelModal items : data) {
                    models.add(items.getTitle());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<List<ModelModal>> call, Throwable t) {

            }
        });

        modelListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Item from index " + i + " has been triggered", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
