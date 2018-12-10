package com.winision.sampleapp.ui.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.winision.sampleapp.ApiInterface;
import com.winision.sampleapp.Clients.ProductClient;
import com.winision.sampleapp.Modals.ProductModal;
import com.winision.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectProduct extends Fragment {

    private ListView productList;
    private ArrayList<String> products = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_product, container, false);

        productList = view.findViewById(R.id.productList);

        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, products);

        productList.setAdapter(adapter);

        ApiInterface apiClient = ProductClient.getProductClient().create(ApiInterface.class);
        Call<List<ProductModal>> call = apiClient.getProductData();

        call.enqueue(new Callback<List<ProductModal>>() {
            @Override
            public void onResponse(Call<List<ProductModal>> call, Response<List<ProductModal>> response) {
                List<ProductModal> data = response.body();
                for (ProductModal items : data) {
                    products.add(items.getTitle());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ProductModal>> call, Throwable t) {

            }
        });

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Item at index " + i + " is triggered", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
